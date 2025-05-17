package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class MapParam(
    override val propName: String,
    val mapKeyType: TypeName = STRING,
    val mapValueType: TypeName = STRING,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = MAP
        .parameterizedBy(mapKeyType, mapValueType)
        .copy(nullable = nullableAssignment)

    override val verifyNotNull: Boolean = false
    override val verifyNotEmpty: Boolean = true

    override fun toPropertySpec(): PropertySpec {
        val type = propTypeName.copy(nullable = nullableAssignment)

        var spec = PropertySpec.Companion.builder(propName, type)
            .addModifiers(accessModifier)
            .mutable(true)

        if (nullableAssignment) {
            spec = spec.initializer("null")
        }

        return spec.build()
    }

    override fun accessors(): List<FunSpec> {
        val pairType = Pair::class.asClassName()
            .parameterizedBy(mapKeyType, mapValueType)

        val spec = ParameterSpec.Companion
            .builder("items", pairType.copy(nullable = false))
            .addModifiers(KModifier.VARARG)
            .build()

        val varargFun = FunSpec.Companion.builder(propName)
            .addParameter(spec) // Usually a non-null list
            .addStatement("this.%N = items.toMap()", propName)
            .build()

        return listOf(varargFun)
    }
}