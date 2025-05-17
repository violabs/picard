package io.violabs.picard.dsl.process

import com.squareup.kotlinpoet.*
import io.violabs.picard.common.Logger
import io.violabs.picard.dsl.params.*

private val DEFAULT_TYPE_NAMES = listOf(
    STRING, INT, LONG, SHORT, BYTE, DOUBLE, FLOAT
)

interface ParameterFactory {
    fun determineParam(
        adapter: ParameterFactoryAdapter,
        isLast: Boolean = false,
        log: Boolean = true
    ): DSLParam
}


class DefaultParameterFactory(val logger: Logger) : ParameterFactory {
    override fun determineParam(adapter: ParameterFactoryAdapter, isLast: Boolean, log: Boolean): DSLParam {
        val propName = adapter.propName
        val actualPropertyType: TypeName = adapter.actualPropTypeName
        val isNullable = actualPropertyType.isNullable
        val nonNullPropType = actualPropertyType.copy(nullable = false)

        val branch = !isLast

        if (log) {
            logger.debug("mapping '$propName'", tier = 3, branch = branch)
            logger.debug("nullable: $isNullable", tier = 4, branch = branch)
        }

        if (adapter.hasSingleEntryTransform) {
            val transformTemplate = adapter.transformTemplate
            val transformType = adapter.transformType

            if (log) {
                logger.debug("SingleEntryTransform", tier = 4, branch = branch)
                logger.debug("template: $transformTemplate", tier = 5, branch = branch)
                logger.debug("type: $transformType", tier = 5, branch = branch)
            }

            if (transformType == null) {
                logger.warn("SingleEntryTransformDSL.inputType is missing or not a KSType.")
                return DefaultParam(propName, actualPropertyType, isNullable)
            }

            val param = SingleTransformParam(
                propName = propName,
                transformTemplate = transformTemplate,
                actualPropTypeName = actualPropertyType,
                inputTypeName = transformType,
                nullableAssignment = adapter.hasNullableAssignment
            )
            if (log) logger.debug("-> SingleTransformParam", tier = 4, branch = branch)
            return param
        }

        val propertyNonNullableClassName: ClassName? = adapter.propertyNonNullableClassName
        val hasGeneratedDSLAnnotation = adapter.hasGeneratedDSLAnnotation

        if (hasGeneratedDSLAnnotation && propertyNonNullableClassName != null) {
            if (log) logger.debug("BuilderParam", tier = 4, branch = branch)
            return createBuilderParam(propName, actualPropertyType, propertyNonNullableClassName, isNullable)
        }

        return when {
            BOOLEAN == nonNullPropType -> {
                if (log) logger.debug("BooleanParam", tier = 4, branch = branch)
                BooleanParam(propName, isNullable)
            }

            DEFAULT_TYPE_NAMES.contains(nonNullPropType) -> {
                if (log) logger.debug("DefaultParam", tier = 4, branch = branch)
                DefaultParam(propName, actualPropertyType, isNullable)
            }

            (nonNullPropType is ParameterizedTypeName && nonNullPropType.rawType == LIST) ||
                (adapter.propertyClassDeclarationQualifiedName == List::class.qualifiedName) -> {
                if (adapter.isGroupElement) {
                    val elementClassName = adapter.groupElementClassName
                        ?: throw IllegalArgumentException("Could not determine group element class name.")
                    if (log) logger.debug("GroupParam", tier = 4, branch = branch)
                    createGroupParam(propName, actualPropertyType, elementClassName, isNullable)
                } else {
                    if (log) logger.debug("ListParam", tier = 4, branch = branch)
                    createListParam(actualPropertyType, propName, isNullable)
                }
            }

            else -> {
                logger.warn("Property '$propName' of type '${actualPropertyType}' could not be mapped to a known DSLParam type. Using DefaultParam as a fallback.")
                val param = DefaultParam(propName, actualPropertyType, isNullable)
                if (log) logger.debug("-> DefaultParam (fallback)", tier = 4, branch = branch)
                param
            }
        }
    }

    private fun createBuilderParam(
        propName: String,
        actualPropertyType: TypeName,
        propertyNonNullableClassName: ClassName, // This is the ClassName of the type that needs a builder
        isNullable: Boolean
    ): BuilderParam {
        val nestedBuilderName = propertyNonNullableClassName.simpleName + "Builder"
        val nestedBuilderClassName = ClassName(propertyNonNullableClassName.packageName, nestedBuilderName)
        logger.debug("nestedBuilder: $nestedBuilderClassName", tier = 5)
        return BuilderParam(propName, actualPropertyType, nestedBuilderClassName, isNullable)
    }


    private fun createGroupParam(
        propName: String,
        actualPropertyType: TypeName, // This is TypeName of the List itself, e.g., List<MyItem>
        listElementClassName: ClassName, // This is ClassName of the element, e.g., MyItem
        isNullable: Boolean
    ): GroupParam {
        logger.debug("listElementClassName: $listElementClassName", tier = 5)
        return GroupParam(propName, actualPropertyType, listElementClassName, isNullable)
    }

    private fun createListParam(
        actualPropertyType: TypeName, // TypeName of the List itself
        propName: String,
        isNullable: Boolean
    ): ListParam {
        return if (actualPropertyType is ParameterizedTypeName && actualPropertyType.rawType == LIST) {
            val elementTypeArgument: TypeName = actualPropertyType.typeArguments.first()
            logger.debug("listElementType: $elementTypeArgument", tier = 5)
            ListParam(propName, elementTypeArgument, isNullable)
        } else {
            // This case should ideally be caught earlier if the type isn't a ParameterizedTypeName
            // or if it's not a list.
            // If actualPropertyType is just a non-parameterized List (raw List), handle appropriately.
            // For safety, if somehow called with a non-parameterized list or non-list:
            logger.warn("Attempted to create ListParam for non-parameterized or non-list type: $actualPropertyType. Falling back to DefaultParam logic for element if possible, or erroring.")
            // A true raw list is rare in Kotlin type declarations. If it's List<*>, typeArguments.first() might be STAR
            // This fallback might need to be more robust or simply throw an error as it indicates an unexpected state.
            throw IllegalArgumentException("Property '$propName' of type '${actualPropertyType}' could not be definitively mapped to ListParam components.")
        }
    }
}