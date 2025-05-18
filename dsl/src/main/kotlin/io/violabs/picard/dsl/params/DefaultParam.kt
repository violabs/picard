package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeName
import io.violabs.picard.dsl.process.ParameterFactoryAdapter

class DefaultParam(
    override val propName: String, // Use a more descriptive name
    actualPropTypeName: TypeName,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = actualPropTypeName.copy(nullable = nullableAssignment)
    override val accessModifier: KModifier = KModifier.PUBLIC

    constructor(adapter: ParameterFactoryAdapter) : this(
        adapter.propName,
        adapter.actualPropTypeName,
        adapter.actualPropTypeName.isNullable
    )
}