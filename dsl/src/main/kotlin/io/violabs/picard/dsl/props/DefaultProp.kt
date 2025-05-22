package io.violabs.picard.dsl.props

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeName
import io.violabs.picard.dsl.process.ParameterFactoryAdapter

/**
 * Basic DSL parameter used when no specialized type matches.
 */
class DefaultProp(
    override val propName: String,
    actualPropTypeName: TypeName,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DslProp {
    override val propTypeName: TypeName = actualPropTypeName.copy(nullable = nullableAssignment)
    // Default parameters are public so generated builders can reference them
    override val accessModifier: KModifier = KModifier.PUBLIC

    constructor(adapter: ParameterFactoryAdapter) : this(
        adapter.propName,
        adapter.actualPropTypeName,
        adapter.actualPropTypeName.isNullable
    )
}