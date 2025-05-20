package io.violabs.picard.dsl.process

import com.squareup.kotlinpoet.*
import io.violabs.picard.common.Logger
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.params.*
import kotlin.reflect.KClass


private val DEFAULT_TYPE_NAMES = listOf(
    CHAR, STRING, BYTE, SHORT, INT, LONG, DOUBLE, FLOAT
)

interface ParameterFactory<T : ParameterFactoryAdapter, P : PropertyAdapter> {
    val logger: Logger

    fun createParameterFactoryAdapter(propertyAdapter: P): T

    fun logAdapter(propertyAdapter: P) {
        val branch = propertyAdapter.continueBranch()
        logger.debug(propertyAdapter.simpleName(), tier = 2, branch = branch, continuous = true)

        val type = propertyAdapter.type
        logger.debug("type:  $type", tier = 3, branch = branch, continuous = true)

        val singleEntryTransform = propertyAdapter.singleEntryTransformString()
        logger.debug("singleEntryTransform: $singleEntryTransform", tier = 3, branch = branch, continuous = true)
    }

    fun determineParam(
        adapter: T,
        isLast: Boolean = false,
        log: Boolean = true
    ): DSLParam
}

class DefaultParameterFactory(logger: Logger) :
    AbstractParameterFactory<DefaultParameterFactoryAdapter, DefaultPropertyAdapter>(logger) {

    override fun createParameterFactoryAdapter(propertyAdapter: DefaultPropertyAdapter): DefaultParameterFactoryAdapter {
        logAdapter(propertyAdapter)

        return DefaultParameterFactoryAdapter(propertyAdapter)
    }
}


abstract class AbstractParameterFactory<T : ParameterFactoryAdapter, P : PropertyAdapter>(
    override val logger: Logger
) : ParameterFactory<T, P> {
    override fun determineParam(adapter: T, isLast: Boolean, log: Boolean): DSLParam {
        val logger = logger.copy(isDebugEnabled = log)
        val propName = adapter.propName
        val actualPropertyType: TypeName = adapter.actualPropTypeName
        val isNullable = actualPropertyType.isNullable
        val nonNullPropType = adapter.nonNullablePropTypeName()

        val branch = !isLast

        logger.debug("mapping '$propName'", tier = 3, branch = branch, continuous = true)
        logger.debug("nullable: $isNullable", tier = 4, branch = branch, continuous = true)

        if (adapter.hasSingleEntryTransform) {
            return buildSingleTransformParam(adapter, log, branch)
        }

        val propertyNonNullableClassName: ClassName? = adapter.propertyNonNullableClassName
        val hasGeneratedDSLAnnotation = adapter.hasGeneratedDSLAnnotation

        if (hasGeneratedDSLAnnotation && propertyNonNullableClassName != null) {
            logger.debug("BuilderParam", tier = 4, branch = branch, continuous = true)
            return createBuilderParam(adapter)
        }

        return when {
            BOOLEAN == nonNullPropType -> {
                logger.debug("BooleanParam", tier = 4, branch = branch, continuous = true)
                BooleanParam(propName, isNullable)
            }

            DEFAULT_TYPE_NAMES.contains(nonNullPropType) -> {
                logger.debug("DefaultParam", tier = 4, branch = branch, continuous = true)
                DefaultParam(propName, actualPropertyType, isNullable)
            }

            checkCollectionType(adapter, MAP, Map::class) -> {
                logger.debug("[CHOICE] map branch", tier = 4, branch = branch, continuous = true)
                if (adapter.mapDetails()?.mapGroupType in GeneratedDSL.MapGroupType.ACTIVE_TYPES) {
                    logger.debug("[DECISION] build MapGroupParam", tier = 4, branch = branch, continuous = true)
                    createMapGroupParam(adapter)
                } else {
                    logger.debug("[DECISION] build MapParam", tier = 4, branch = branch, continuous = true)
                    createMapParam(adapter)
                }
            }

            checkCollectionType(adapter, LIST, List::class) -> {
                logger.debug("[CHOICE] list branch", tier = 4, branch = branch, continuous = true)
                if (adapter.isGroupElement) {
                    logger.debug("[DECISION] build GroupParam", tier = 4, branch = branch, continuous = true)
                    createGroupParam(adapter)
                } else {
                    logger.debug("[DECISION] build ListParam", tier = 4, branch = branch, continuous = true)
                    createListParam(adapter)
                }
            }

            else -> {
                logger.warn("Property '$propName' of type '${actualPropertyType}' could not be mapped to a known DSLParam type. Using DefaultParam as a fallback.")
                val param = DefaultParam(propName, actualPropertyType, isNullable)
                logger.debug("-> DefaultParam (fallback)", tier = 4, branch = branch, continuous = true)
                param
            }
        }
    }

    private fun checkCollectionType(
        adapter: T,
        expectedType: TypeName,
        expectedClass: KClass<*>
    ): Boolean {
        val nonNullPropType = adapter.nonNullablePropTypeName()
        val isRawCollection = nonNullPropType is ParameterizedTypeName && nonNullPropType.rawType == expectedType
        val isQualifiedCollection = adapter.propertyClassDeclarationQualifiedName == expectedClass.qualifiedName

        return isRawCollection || isQualifiedCollection
    }

    private fun buildSingleTransformParam(
        adapter: ParameterFactoryAdapter,
        log: Boolean = true,
        branch: Boolean = true
    ): DSLParam {
        val transformType = adapter.transformType

        logger.debug("SingleEntryTransform", tier = 4, branch = branch, continuous = true)
        logger.debug("template: ${adapter.transformTemplate}", tier = 5, branch = branch, continuous = true)
        logger.debug("type: $transformType", tier = 5, branch = branch, continuous = true)

        if (transformType == null) {
            logger.warn("SingleEntryTransformDSL.inputType is missing or not a KSType.")
            return DefaultParam(adapter)
        }

        if (log) logger.debug("-> SingleTransformParam", tier = 4, branch = branch, continuous = true)
        return SingleTransformParam(adapter)
    }

    private fun createBuilderParam(
        adapter: T,
    ): BuilderParam {
        val propertyNonNullableClassName = requireNotNull(adapter.propertyNonNullableClassName) {
            "Could not determine property non-nullable class name."
        }
        val nestedBuilderName = propertyNonNullableClassName.simpleName + "Builder"
        val nestedBuilderClassName = ClassName(propertyNonNullableClassName.packageName, nestedBuilderName)
        logger.debug("nestedBuilder: $nestedBuilderClassName", tier = 5, continuous = true)
        return BuilderParam(
            adapter.propName,
            adapter.actualPropTypeName,
            nestedBuilderClassName,
            adapter.hasNullableAssignment
        )
    }

    private fun createGroupParam(adapter: T): GroupParam {
        val groupElementClassName = requireNotNull(adapter.groupElementClassName) {
            "Could not determine group element class name."
        }
        logger.debug("listElementClassName: $groupElementClassName", tier = 5, continuous = true)
        return GroupParam(
            adapter.propName,
            adapter.actualPropTypeName,
            groupElementClassName,
            adapter.hasNullableAssignment
        )
    }

    private fun createMapGroupParam(adapter: T): MapGroupParam {
        val mapDetails = requireNotNull(adapter.mapDetails) { "Please add map details to the map parameter" }

        return MapGroupParam(
            adapter.propName,
            mapDetails.keyType,
            mapDetails.valueType,
            adapter.hasNullableAssignment
        )
    }

    private fun createMapParam(adapter: T): MapParam {
        val propName = adapter.propName
        val actualPropertyType: TypeName = adapter.actualPropTypeName
        return if (actualPropertyType is ParameterizedTypeName && actualPropertyType.rawType == MAP) {
            val elementKeyTypeArgument: TypeName = actualPropertyType.typeArguments.first()
            val elementValueTypeArgument: TypeName = actualPropertyType.typeArguments.last()
            logger.debug("mapElementKey: $elementKeyTypeArgument", tier = 5, continuous = true)
            logger.debug("mapElementValue: $elementValueTypeArgument", tier = 5, continuous = true)
            MapParam(propName, elementKeyTypeArgument, elementValueTypeArgument, adapter.hasNullableAssignment)
        } else {
            // This case should ideally be caught earlier if the type isn't a ParameterizedTypeName
            // or if it's not a list.
            // If actualPropertyType is just a non-parameterized List (raw List), handle appropriately.
            // For safety, if somehow called with a non-parameterized list or non-list:
            logger.warn(
                "Attempted to create ListParam for non-parameterized or non-list type: $actualPropertyType. " +
                    "Falling back to DefaultParam logic for element if possible, or erroring."
            )
            // A true raw list is rare in Kotlin type declarations. If it's List<*>, typeArguments.first() might be STAR
            // This fallback might need to be more robust or simply throw an error as it indicates an unexpected state.
            throw IllegalArgumentException(
                "Property '$propName' of type '${actualPropertyType}' could not be definitively " +
                    "mapped to ListParam components.")
        }
    }

    private fun createListParam(adapter: T): ListParam {
        val propName = adapter.propName
        val actualPropertyType: TypeName = adapter.actualPropTypeName
        return if (actualPropertyType is ParameterizedTypeName && actualPropertyType.rawType == LIST) {
            val elementTypeArgument: TypeName = actualPropertyType.typeArguments.first()
            logger.debug("listElementType: $elementTypeArgument", tier = 5, continuous = true)
            ListParam(propName, elementTypeArgument, adapter.hasNullableAssignment)
        } else {
            // This case should ideally be caught earlier if the type isn't a ParameterizedTypeName
            // or if it's not a list.
            // If actualPropertyType is just a non-parameterized List (raw List), handle appropriately.
            // For safety, if somehow called with a non-parameterized list or non-list:
            logger.warn(
                "Attempted to create ListParam for non-parameterized or non-list type: $actualPropertyType. " +
                    "Falling back to DefaultParam logic for element if possible, or erroring."
            )
            // A true raw list is rare in Kotlin type declarations. If it's List<*>, typeArguments.first() might be STAR
            // This fallback might need to be more robust or simply throw an error as it indicates an unexpected state.
            throw IllegalArgumentException(
                "Property '$propName' of type '${actualPropertyType}' could not be definitively " +
                    "mapped to ListParam components.")
        }
    }
}