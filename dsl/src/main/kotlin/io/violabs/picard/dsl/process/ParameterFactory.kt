package io.violabs.picard.dsl.process

import com.squareup.kotlinpoet.*
import com.google.devtools.ksp.symbol.KSClassDeclaration
import io.violabs.picard.common.Logger
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.params.*
import kotlin.reflect.KClass


private val DEFAULT_TYPE_NAMES = listOf(
    CHAR, STRING, BYTE, SHORT, INT, LONG, DOUBLE, FLOAT
)

/**
 * Responsible for creating [DSLParam] instances for a given property adapter.
 */
interface ParameterFactory<T : ParameterFactoryAdapter, P : PropertyAdapter> {
    /** logger used for debug output */
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

    /**
     * Resolve the correct [DSLParam] implementation for the provided adapter.
     *
     * @param adapter the property adapter being processed
     * @param isLast whether this is the last parameter being generated
     * @param log enable debug logging for this invocation
     */
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


/**
 * Base implementation of [ParameterFactory] with common resolution logic.
 */
abstract class AbstractParameterFactory<T : ParameterFactoryAdapter, P : PropertyAdapter>(
    override val logger: Logger
) : ParameterFactory<T, P> {
    /** @inheritdoc */
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
        val kdoc = builderDoc(nestedBuilderClassName, adapter.propertyClassDeclaration)
        
        return BuilderParam(
            adapter.propName,
            adapter.actualPropTypeName,
            nestedBuilderClassName,
            adapter.hasNullableAssignment,
            kdoc = kdoc
        )
    }

    private fun createGroupParam(adapter: T): GroupParam {
        val groupElementClassName = requireNotNull(adapter.groupElementClassName) {
            "Could not determine group element class name."
        }
        logger.debug("listElementClassName: $groupElementClassName", tier = 5, continuous = true)
        val builderClassName = ClassName(groupElementClassName.packageName, groupElementClassName.simpleName + "Builder")
        val kdoc = builderDoc(builderClassName, adapter.groupElementClassDeclaration)
        return GroupParam(
            adapter.propName,
            adapter.actualPropTypeName,
            groupElementClassName,
            adapter.hasNullableAssignment,
            kdoc = kdoc
        )
    }

    private fun createMapGroupParam(adapter: T): MapGroupParam {
        val mapDetails = requireNotNull(adapter.mapDetails) { "Please add map details to the map parameter" }
        val kdoc = builderDoc(mapDetails.valueClass(), adapter.mapValueClassDeclaration)

        return MapGroupParam(
            adapter.propName,
            mapDetails.keyType,
            mapDetails.valueType,
            adapter.hasNullableAssignment,
            kdoc = kdoc
        )
    }

    private fun builderDoc(builderClass: ClassName, declaration: KSClassDeclaration?): String? {
        val props = declaration?.getAllProperties()?.map { it.simpleName.asString() }?.toList() ?: return null

        if (props.isEmpty()) return null
        val list = props.sorted().joinToString("\n") { "* [${builderClass.simpleName}.$it]" }
        return "Available builder functions:\n$list"
    }

    /**
     * Build a [MapParam] from the adapter when the property is a Map type.
     * Falls back to [DefaultParam] when the type information is insufficient.
     */
    private fun createMapParam(adapter: T): DSLParam {
        val propName = adapter.propName
        val actualPropertyType: TypeName = adapter.actualPropTypeName

        if (actualPropertyType is ParameterizedTypeName && actualPropertyType.rawType == MAP) {
            val keyType: TypeName = actualPropertyType.typeArguments.first()
            val valueType: TypeName = actualPropertyType.typeArguments.last()
            logger.debug("mapElementKey: $keyType", tier = 5, continuous = true)
            logger.debug("mapElementValue: $valueType", tier = 5, continuous = true)
            return MapParam(propName, keyType, valueType, adapter.hasNullableAssignment)
        }

        logger.warn(
            "Attempted to create MapParam for unsupported type '$actualPropertyType'. Falling back to DefaultParam."
        )
        return DefaultParam(propName, actualPropertyType, adapter.hasNullableAssignment)
    }

    /**
     * Build a [ListParam] from the adapter when the property is a List type.
     * Falls back to [DefaultParam] when the type information is insufficient.
     */
    private fun createListParam(adapter: T): DSLParam {
        val propName = adapter.propName
        val actualPropertyType: TypeName = adapter.actualPropTypeName

        if (actualPropertyType is ParameterizedTypeName && actualPropertyType.rawType == LIST) {
            val elementTypeArgument: TypeName = actualPropertyType.typeArguments.first()
            logger.debug("listElementType: $elementTypeArgument", tier = 5, continuous = true)
            return ListParam(propName, elementTypeArgument, adapter.hasNullableAssignment)
        }

        logger.warn(
            "Attempted to create ListParam for unsupported type '$actualPropertyType'. Falling back to DefaultParam."
        )
        return DefaultParam(propName, actualPropertyType, adapter.hasNullableAssignment)
    }
}