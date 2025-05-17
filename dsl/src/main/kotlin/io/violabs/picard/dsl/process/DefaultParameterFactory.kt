package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeArgument
import com.squareup.kotlinpoet.BOOLEAN
import com.squareup.kotlinpoet.BYTE
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.DOUBLE
import com.squareup.kotlinpoet.FLOAT
import com.squareup.kotlinpoet.INT
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.LONG
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.SHORT
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import io.violabs.picard.common.Logger
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.annotation.GeneratedGroupDSL
import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL
import io.violabs.picard.dsl.params.BooleanParam
import io.violabs.picard.dsl.params.BuilderParam
import io.violabs.picard.dsl.params.DSLParam
import io.violabs.picard.dsl.params.DefaultParam
import io.violabs.picard.dsl.params.GroupParam
import io.violabs.picard.dsl.params.ListParam
import io.violabs.picard.dsl.params.SingleTransformParam

private val DEFAULT_TYPE_NAMES = listOf(
    STRING, INT, LONG, SHORT, BYTE, DOUBLE, FLOAT
)

interface ParameterFactory {
    fun determineParam(
        prop: KSPropertyDeclaration,
        singleEntryTransform: KSClassDeclaration?,
        logger: KSPLogger
    ): DSLParam
}

interface ParameterFactory2 {
    fun determineParam(
        adapter: ParameterFactoryAdapter,
        isLast: Boolean = false,
        log: Boolean = true
    ): DSLParam
}


class DefaultParameterFactory2(val logger: Logger) : ParameterFactory2 {
    override fun determineParam(adapter: ParameterFactoryAdapter, isLast: Boolean, log: Boolean): DSLParam {
        val propName = adapter.propName
        val actualPropertyType: TypeName = adapter.actualPropTypeName
        val isNullable = actualPropertyType.isNullable
        val nonNullPropType = actualPropertyType.copy(nullable = false)

        if (adapter.hasSingleEntryTransform) {
            val transformTemplate = adapter.transformTemplate
            val transformType = adapter.transformType

            if (transformType == null) {
                logger.warn("SingleEntryTransformDSL.inputType is missing or not a KSType.")
                return DefaultParam(propName, actualPropertyType, isNullable)
            }

            return SingleTransformParam(
                propName = propName,
                transformTemplate = transformTemplate,
                actualPropTypeName = actualPropertyType,
                inputTypeName = transformType,
                nullableAssignment = adapter.hasNullableAssignment
            )
        }

        val propertyNonNullableClassName: ClassName? = adapter.propertyNonNullableClassName
        val hasGeneratedDSLAnnotation = adapter.hasGeneratedDSLAnnotation

        if (hasGeneratedDSLAnnotation && propertyNonNullableClassName != null) {
            return createBuilderParam(propName, actualPropertyType, propertyNonNullableClassName, isNullable)
        }

        return when {
            BOOLEAN == nonNullPropType -> BooleanParam(propName, isNullable)
            DEFAULT_TYPE_NAMES.contains(nonNullPropType) -> DefaultParam(propName, actualPropertyType, isNullable)
            (nonNullPropType is ParameterizedTypeName && nonNullPropType.rawType == LIST) ||
                (adapter.propertyClassDeclarationQualifiedName == List::class.qualifiedName) -> {
                    if (adapter.isGroupElement) {
                        val elementClassName = adapter.groupElementClassName
                            ?: throw IllegalArgumentException("Could not determine group element class name.")
                        createGroupParam(propName, actualPropertyType, elementClassName, isNullable)
                    } else {
                        createListParam(actualPropertyType, propName, isNullable)
                    }
                }
            else -> {
                logger.warn("Property '$propName' of type '${actualPropertyType}' could not be mapped to a known DSLParam type. Using DefaultParam as a fallback.")
                DefaultParam(propName, actualPropertyType, isNullable)
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
        return BuilderParam(propName, actualPropertyType, nestedBuilderClassName, isNullable)
    }


    private fun createGroupParam(
        propName: String,
        actualPropertyType: TypeName, // This is TypeName of the List itself, e.g., List<MyItem>
        listElementClassName: ClassName, // This is ClassName of the element, e.g., MyItem
        isNullable: Boolean
    ): GroupParam {
        return GroupParam(propName, actualPropertyType, listElementClassName, isNullable)
    }

    private fun createListParam(
        actualPropertyType: TypeName, // TypeName of the List itself
        propName: String,
        isNullable: Boolean
    ): ListParam {
        return if (actualPropertyType is ParameterizedTypeName && actualPropertyType.rawType == LIST) {
            val elementTypeArgument: TypeName = actualPropertyType.typeArguments.first()
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

class DefaultParameterFactory(val logger: KSPLogger) : ParameterFactory {
    override fun determineParam(
        prop: KSPropertyDeclaration,
        singleEntryTransform: KSClassDeclaration?,
        logger: KSPLogger
    ): DSLParam {
        val propName = prop.simpleName.asString()
        val actualPropertyType: TypeName = prop.type.toTypeName() // KotlinPoet TypeName
        val isNullable = actualPropertyType.isNullable
        val nonNullPropType = actualPropertyType.copy(nullable = false)

        val resolvedPropKSType: KSType = prop.type.resolve() // KSP KSType

        if (singleEntryTransform != null) {
            val annotation = singleEntryTransform
                .annotations
                .find { it.shortName.asString() == SingleEntryTransformDSL::class.simpleName }

            val transformTemplate = annotation
                ?.arguments
                ?.firstOrNull { it.name?.asString() == "transformTemplate" }
                ?.value
                ?.toString()
                ?.takeIf { it.isNotBlank() }


            val transformType = annotation
                ?.arguments
                ?.firstOrNull { it.name?.asString() == "inputType" }
                ?.value as? KSType // Ensure it's KSType

            if (transformType == null) {
                logger.error("SingleEntryTransformDSL.inputType is missing or not a KSType.", singleEntryTransform)
                // Fallback or throw error
                return DefaultParam(propName, actualPropertyType, isNullable)
            }

            return SingleTransformParam(
                propName = propName,
                transformTemplate = transformTemplate,
                actualPropTypeName = actualPropertyType,
                inputTypeName = transformType.toTypeName(),
                nullableAssignment = resolvedPropKSType.isMarkedNullable
            )
        }

        val propertyClassDeclaration = resolvedPropKSType.declaration as? KSClassDeclaration
        val propertyNonNullableClassName: ClassName? = propertyClassDeclaration?.toClassName()

        val hasGeneratedDSLAnnotation = propertyClassDeclaration?.annotations?.any {
            it.shortName.asString() == GeneratedDSL::class.simpleName
        } ?: false

        if (hasGeneratedDSLAnnotation && propertyNonNullableClassName != null) {
            return createBuilderParam(propName, actualPropertyType, propertyNonNullableClassName, isNullable)
        }

        return when {
            BOOLEAN == nonNullPropType -> BooleanParam(propName, isNullable)
            DEFAULT_TYPE_NAMES.contains(nonNullPropType) -> DefaultParam(propName, actualPropertyType, isNullable)
            // Check if it's a List by comparing the raw type of nonNullPropType (which is a TypeName)
            // or by checking the KSType's declaration
            (nonNullPropType is ParameterizedTypeName && nonNullPropType.rawType == LIST) ||
                (propertyClassDeclaration?.qualifiedName?.asString() == List::class.qualifiedName) -> {
                // It's a list, now check the element type for @GeneratedGroupDSL
                val listElementKSTypeArg: KSTypeArgument? = resolvedPropKSType.arguments.firstOrNull()
                val listElementKSType: KSType? = listElementKSTypeArg?.type?.resolve()
                val listElementClassDecl = listElementKSType?.declaration as? KSClassDeclaration

                val isGroupElement = listElementClassDecl?.annotations?.any {
                    it.shortName.asString() == GeneratedGroupDSL::class.simpleName
                } ?: false

                if (isGroupElement) {
                    val elementClassName = listElementClassDecl.toClassName()
                    createGroupParam(propName, actualPropertyType, elementClassName, isNullable)
                } else {
                    createListParam(actualPropertyType, propName, isNullable)
                }
            }
            // Assuming "Group" types (that are not lists but might have custom builders)
            // This check might be too broad or might need to be more specific,
            // e.g., based on an annotation rather than just "Group" in the name.
            propertyNonNullableClassName != null && "Group" in propertyNonNullableClassName.simpleName && !hasGeneratedDSLAnnotation -> {
                // This logic might need refinement. If a class is named "XyzGroup" and isn't a List<@GeneratedGroupDSL Item>,
                // how should it be handled? If it also needs a builder, it might need @GeneratedDSL.
                // For now, assuming it's a distinct non-list group type.
                val groupBuilderName = propertyNonNullableClassName.simpleName + "Builder"
                val groupBuilderClassName = ClassName(propertyNonNullableClassName.packageName, groupBuilderName)
                GroupParam(propName, actualPropertyType, groupBuilderClassName, isNullable) // Potentially ambiguous with List-based groups
            }
            else -> {
                logger.warn("Property '$propName' of type '${actualPropertyType}' could not be mapped to a known DSLParam type. Using DefaultParam as a fallback.")
                DefaultParam(propName, actualPropertyType, isNullable)
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
        return BuilderParam(propName, actualPropertyType, nestedBuilderClassName, isNullable)
    }

    private fun createGroupParam(
        propName: String,
        actualPropertyType: TypeName, // This is TypeName of the List itself, e.g., List<MyItem>
        listElementClassName: ClassName, // This is ClassName of the element, e.g., MyItem
        isNullable: Boolean
    ): GroupParam {
        // The builder is for the element type of the list
        val elementBuilderName = listElementClassName.simpleName + "Builder"
        val elementBuilderClassName = ClassName(listElementClassName.packageName, elementBuilderName)
        return GroupParam(propName, actualPropertyType, elementBuilderClassName, isNullable)
    }

    private fun createListParam(
        actualPropertyType: TypeName, // TypeName of the List itself
        propName: String,
        isNullable: Boolean
    ): ListParam {
        return if (actualPropertyType is ParameterizedTypeName && actualPropertyType.rawType == LIST) {
            val elementTypeArgument: TypeName = actualPropertyType.typeArguments.first()
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