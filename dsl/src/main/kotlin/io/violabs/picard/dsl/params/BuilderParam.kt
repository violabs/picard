package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*

class BuilderParam(
    override val propName: String,
    private val originalPropertyType: TypeName,
    private val nestedBuilderClassName: ClassName,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType // Type of the field in the builder

    override fun accessors(): List<FunSpec> {
        val blockParam = ParameterSpec.Companion.builder(
            "block", LambdaTypeName.Companion.get(
                receiver = nestedBuilderClassName,  // THIS IS THE KEY: uses the specific builder type
                parameters = emptyList(),
                returnType = UNIT
            )
        ).build()

        val funSpec = FunSpec.Companion.builder(propName) // Setter method name
            .addParameter(blockParam)
            .addStatement("val builder = %T()", nestedBuilderClassName) // Instantiates the specific builder
            .addStatement("builder.block()")
            .addStatement("this.%N = builder.build()", propName) // Assigns the built object
            .build()

        return listOf(funSpec)
    }
}