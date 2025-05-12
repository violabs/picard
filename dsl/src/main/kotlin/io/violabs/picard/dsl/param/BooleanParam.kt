package io.violabs.picard.dsl.param

import com.squareup.kotlinpoet.*

class BooleanParam(
    override val propName: String,
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = BOOLEAN.copy(nullable = nullable) // Correctly use constructor arg

    override fun accessors(): List<FunSpec> {
        val param = ParameterSpec.Companion
            .builder("on", BOOLEAN) // Non-nullable for the setter
            .defaultValue("true")
            .build()

        return FunSpec.Companion.builder(propName)
            .addParameter(param)
            .addStatement("this.%N = %N", propName, param) // Return the builder itself
            .build()
            .let { listOf(it) }
    }
}