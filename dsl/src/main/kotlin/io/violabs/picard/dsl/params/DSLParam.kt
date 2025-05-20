package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import io.violabs.picard.dsl.builder.kotlinPoet

/**
 * Represents a property in a generated DSL builder.
 */
interface DSLParam {
    val propName: String
    val functionName: String get() = propName
    val propTypeName: TypeName // This should be the type of the actual property in the domain object
    val nullableAssignment: Boolean get() = true
    val nullableProp: Boolean get() = true
    val verifyNotNull: Boolean get() = true
    val verifyNotEmpty: Boolean get() = false
    val accessModifier: KModifier get() = KModifier.PRIVATE

    /**
     * Create the KotlinPoet [PropertySpec] representing this DSL property.
     */
    fun toPropertySpec(): PropertySpec = kotlinPoet {
        property {
            accessModifier(accessModifier)
            variable()
            name = propName
            type(propTypeName.copy(nullable = nullableProp))

            if (nullableProp) initNullValue()
        }
    }

    // Added containingBuilderClassName to allow fluent return types
    /**
     * Generate any accessor functions (such as DSL builder methods) for this parameter.
     */
    fun accessors(): List<FunSpec> {
        return emptyList()
    }

    /**
     * Provide the code snippet used when returning this parameter's value.
     */
    fun propertyValueReturn(): String {
        if (nullableAssignment) return propName

        return if (verifyNotNull) {
            "vRequireNotNull(::$propName)" // Added message for vRequireNotNull
        } else if (verifyNotEmpty) {
            "vRequireNotEmpty(::$propName)"
        } else {
            propName
        }
    }
}