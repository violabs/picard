package io.violabs.picard.dsl.params

import com.google.common.base.Defaults.defaultValue
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

// Assuming GroupParam is similar to BuilderParam or a complex type needing its own builder
class GroupParam(
    override val propName: String,
    private val originalPropertyType: TypeName, // e.g., MyGroupType?
    private val groupBuilderClassName: ClassName, // e.g., MyGroupTypeBuilder
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType

    override fun toPropertySpec(): PropertySpec {
        return PropertySpec.Companion.builder(propName, MUTABLE_LIST.parameterizedBy(groupBuilderClassName))
            .addModifiers(accessModifier)
            .mutable(true)
            .initializer(null)
            .build()
    }


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