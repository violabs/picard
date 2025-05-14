package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName

class SingleTransformParam(
    override val propName: String, // Use a more descriptive name
    val inputTypeName: TypeName,
    actualPropTypeName: TypeName,
    val transformTemplate: String? = null,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = actualPropTypeName.copy(nullable = nullableAssignment)
    override val accessModifier: KModifier = KModifier.PUBLIC

    override fun accessors(): List<FunSpec> {
        val param = ParameterSpec.Companion
            .builder(propName, inputTypeName)
            .build()

        val finalTransformTemplate = transformTemplate ?: "${propTypeName.copy(nullable = false)}(%N)"

        return FunSpec.Companion.builder(propName)
            .addParameter(param)
            .addStatement("this.%N = $finalTransformTemplate", propName, param)
            .build()
            .let { listOf(it) }
    }
}