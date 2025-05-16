package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
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
import kotlin.collections.contains

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

class DefaultParameterFactory : ParameterFactory {
    // Pass logger for potential warnings/errors
    override fun determineParam(
        prop: KSPropertyDeclaration,
        singleEntryTransform: KSClassDeclaration?,
        logger: KSPLogger
    ): DSLParam {
        val propName = prop.simpleName.asString()
        // Get the TypeName for the property as it's declared (respecting its nullability)
        val actualPropertyType = prop.type.toTypeName()
        val isNullable = actualPropertyType.isNullable
        val nonNullPropType = actualPropertyType.copy(nullable = false)

        println("------- --------- ${prop.type} - annotations: ${prop.type.annotations.toList()}")
        if (propName == "Passenger") {
            println("IS PASSENGER --------- $propName - annotations: ${prop.annotations.toList()}")
        }
        // For deriving builder names, we need the non-nullable ClassName of the type
        val resolvedType = prop.type.resolve()

        if (singleEntryTransform != null) {
            println("IS SINGLE ENTRY TRANSFORM")
            val annotation = singleEntryTransform
                .annotations
                .find { it.shortName.asString() == SingleEntryTransformDSL::class.simpleName }

            val transformTemplate = annotation
                ?.arguments
                ?.firstOrNull { it.name?.asString() == "transformTemplate" }
                ?.value
                ?.takeIf { it.toString().isNotBlank() }
                ?.toString()

            val transformType = annotation
                ?.arguments
                ?.firstOrNull { it.name?.asString() == "inputType" }
                ?.value as KSType

            // Create a DSLParam that uses the transformer
            return SingleTransformParam(
                propName = prop.simpleName.asString(),
                transformTemplate = transformTemplate,
                actualPropTypeName = prop.type.toTypeName(),
                inputTypeName = transformType.toTypeName(),
                nullableAssignment = prop.type.resolve().isMarkedNullable
            )
        }
        val propertyClassDeclaration = resolvedType.declaration as? KSClassDeclaration
        val propertyNonNullableClassName = propertyClassDeclaration?.toClassName()

        // Check if the class type of the property has the GenerateDSL annotation
        val hasGeneratedDSLAnnotation = propertyClassDeclaration?.annotations?.any {
            it.shortName.asString() == GeneratedDSL::class.simpleName
        } ?: false

        if (hasGeneratedDSLAnnotation && propertyNonNullableClassName != null) {
            return createBuilderParam(propName, actualPropertyType, propertyNonNullableClassName, isNullable)
        }

        return when {
            BOOLEAN == nonNullPropType -> BooleanParam(propName, isNullable)
            DEFAULT_TYPE_NAMES.contains(nonNullPropType) -> DefaultParam(propName, actualPropertyType, isNullable)
            LIST.toString() in nonNullPropType.toString() -> createListOrGroup(actualPropertyType, propName, propertyClassDeclaration!!.toClassName(), isNullable)
            // Assuming "Group" types also have builders following the "TypeNameBuilder" convention
            propertyNonNullableClassName != null && "Group" in propertyNonNullableClassName.simpleName -> {
                val groupBuilderName = propertyNonNullableClassName.simpleName + "Builder"
                val groupBuilderClassName = ClassName(propertyNonNullableClassName.packageName, groupBuilderName)
                GroupParam(propName, actualPropertyType, groupBuilderClassName, isNullable)
            }
            // Fallback for types not covered (e.g. functional types, type aliases not resolving to classes)
            else -> {
                logger.warn("Property '$propName' of type '${actualPropertyType}' could not be mapped to a known DSLParam type. Using DefaultParam as a fallback.")
                DefaultParam(propName, actualPropertyType, isNullable)
            }
        }
    }

    private fun createBuilderParam(
        propName: String,
        actualPropertyType: TypeName,
        propertyNonNullableClassName: ClassName,
        isNullable: Boolean = true
    ): BuilderParam {
        val nestedBuilderName = propertyNonNullableClassName.simpleName + "Builder"
        // The nested builder will be in the same package as its corresponding domain class
        val nestedBuilderClassName = ClassName(propertyNonNullableClassName.packageName, nestedBuilderName)
        return BuilderParam(propName, actualPropertyType, nestedBuilderClassName, isNullable)
    }

    private fun createListOrGroup(
        actualPropertyType: TypeName,
        propName: String,
        propertyNonNullableClassName: ClassName,
        isNullable: Boolean = true
    ): DSLParam {
        if (actualPropertyType is ParameterizedTypeName) {
            val parameterizedType: TypeName = actualPropertyType.typeArguments.first()

            val isGroup = parameterizedType.annotations
                .any { it.typeName.toString() == GeneratedGroupDSL::class.simpleName.toString() }

            if (isGroup) {
                return createGroupParma(
                    propName,
                    actualPropertyType,
                    propertyNonNullableClassName,
                    isNullable
                )
            }
        }

        return createListParam(actualPropertyType, propName, isNullable)
    }

    private fun createGroupParma(
        propName: String,
        actualPropertyType: TypeName,
        propertyNonNullableClassName: ClassName,
        isNullable: Boolean = true
    ): GroupParam {
        val nestedBuilderName = propertyNonNullableClassName.simpleName + "Builder"
        // The nested builder will be in the same package as its corresponding domain class
        val nestedBuilderClassName = ClassName(propertyNonNullableClassName.packageName, nestedBuilderName)
        return GroupParam(propName, actualPropertyType, nestedBuilderClassName, isNullable)
    }

    private fun createListParam(
        actualPropertyType: TypeName,
        propName: String,
        isNullable: Boolean = true
    ): ListParam {
        return if (actualPropertyType is ParameterizedTypeName) {
            val args = actualPropertyType.typeArguments.first()

            ListParam(propName, args, isNullable)
        } else {
            throw IllegalArgumentException("Property '$propName' of type '${actualPropertyType}' could not be mapped to a known DSLParam type.")
        }
    }
}