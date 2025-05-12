package io.violabs.picard.dsl.param

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class ListParam(
    override val propName: String,
    val collectionType: TypeName = STRING,
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = LIST.parameterizedBy(collectionType).copy(nullable = nullable)

    override val verifyNotNull: Boolean = false
    override val verifyNotEmpty: Boolean = true

    // Example for a list setter (could be more sophisticated, e.g., vararg)
    override fun accessors(): List<FunSpec> {
        val spec = ParameterSpec.Companion
            .builder("items", collectionType.copy(nullable = false))
            .addModifiers(KModifier.VARARG)
            .build()

        return FunSpec.Companion.builder(propName)
            .addParameter(spec) // Usually a non-null list
            .addStatement("this.%N = items.toList()", propName)
            .build()
            .let { listOf(it) }
    }
}