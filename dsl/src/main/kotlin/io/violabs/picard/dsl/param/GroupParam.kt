package io.violabs.picard.dsl.param

import com.squareup.kotlinpoet.*

// Assuming GroupParam is similar to BuilderParam or a complex type needing its own builder
class GroupParam(
    override val propName: String,
    private val originalPropertyType: TypeName, // e.g., MyGroupType?
    private val groupBuilderClassName: ClassName, // e.g., MyGroupTypeBuilder
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
            .addStatement("val builder = %T()", groupBuilderClassName)
            .addStatement("builder.block()")
            .addStatement("this.%N = builder.build()", propName)
            .build()
        return listOf(funSpec)
    }
}