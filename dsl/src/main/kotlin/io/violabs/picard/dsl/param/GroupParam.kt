package io.violabs.picard.dsl.param

import com.squareup.kotlinpoet.*

class GroupParam(
    override val propName: String,
    originalPropertyType: TypeName,
    private val groupBuilderClassName: ClassName,
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType

    override fun toPropertySpec(): PropertySpec = PropertySpec.Companion.builder(propName, propTypeName)
        .addModifiers(KModifier.PRIVATE)
        .mutable(true)
        .initializer("null")
        .build()

    override fun accessors(): List<FunSpec> {
        val blockParam = ParameterSpec.Companion.builder(
            "block", LambdaTypeName.Companion.get(
                receiver = groupBuilderClassName,
                parameters = emptyList(),
                returnType = UNIT
            )
        ).build()

        val funSpec = FunSpec.Companion.builder(propName)
            .addParameter(blockParam)
            .addStatement("this.%N = $groupBuilderClassName().apply(block).items()", propName)
            .build()
        return listOf(funSpec)
    }
}