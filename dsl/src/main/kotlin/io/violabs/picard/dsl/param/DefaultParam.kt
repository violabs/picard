package io.violabs.picard.dsl.param

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeName

class DefaultParam(
    override val propName: String, // Use a more descriptive name
    actualPropTypeName: TypeName,
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = actualPropTypeName.copy(nullable = nullable)
    override val accessModifier: KModifier = KModifier.PUBLIC
}