package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

// Assuming GroupParam is similar to BuilderParam or a complex type needing its own builder
class GroupParam(
    override val propName: String,
    private val originalPropertyType: TypeName, // e.g., MyGroupType?
    private val builtClassName: ClassName, // e.g., MyGroupTypeBuilder
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType

    override fun toPropertySpec(): PropertySpec {
        val assignmentType = LIST.parameterizedBy(builtClassName).copy(nullable = true)

        return PropertySpec.Companion.builder(propName, assignmentType)
            .addModifiers(accessModifier)
            .mutable(true)
            .initializer("null")
            .build()
    }


    override fun accessors(): List<FunSpec> {
        val groupBuilderClassName = ClassName(builtClassName.packageName, builtClassName.simpleName, "Builder")
        val receiverName = ClassName(groupBuilderClassName.packageName, builtClassName.simpleName + "Builder", "Group")

        val blockParam = ParameterSpec.Companion.builder(
            "block", LambdaTypeName.Companion.get(
                receiver = receiverName,
                parameters = emptyList(),
                returnType = UNIT
            )
        ).build()

        val funSpec = FunSpec.Companion.builder(propName)
            .addParameter(blockParam)
            .addStatement("this.%N = $receiverName().apply(block).items()", propName)
            .build()
        return listOf(funSpec)
    }
}