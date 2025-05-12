package io.violabs.picard.dsl.param

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName

interface DSLParam {
    val propName: String
    val propTypeName: TypeName // This should be the type of the actual property in the domain object
    val nullable: Boolean get() = true
    val verifyNotNull: Boolean get() = true
    val verifyNotEmpty: Boolean get() = false
    val accessModifier: KModifier get() = KModifier.PRIVATE

    fun toPropertySpec(): PropertySpec {
        val type = propTypeName.copy(nullable = nullable)

        var spec = PropertySpec.Companion.builder(propName, type)
            .addModifiers(accessModifier)
            .mutable(true)

        if (nullable) {
            spec = spec.initializer("null")
        }

        return spec.build()
    }

    // Added containingBuilderClassName to allow fluent return types
    fun accessors(): List<FunSpec> {
        return emptyList()
    }

    fun propertyValueReturn(): String {
        if (nullable) return propName

        return if (verifyNotNull) {
            "vRequireNotNull(::$propName, \"$propName\")" // Added message for vRequireNotNull
        } else if (verifyNotEmpty) {
            "vRequireNotEmpty(::$propName, \"$propName\")"
        } else {
            propName
        }
    }
}