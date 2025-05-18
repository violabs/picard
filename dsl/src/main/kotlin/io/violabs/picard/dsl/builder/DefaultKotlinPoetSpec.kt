package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.KModifier
import io.violabs.picard.dsl.builder.KPPropertySpecBuilder.Companion.ALL_ACCESS_MODIFIERS

internal interface KotlinPoetSpec {
    var name: String?
    val modifiers: MutableList<KModifier>
}

internal abstract class DefaultKotlinPoetSpec : KotlinPoetSpec {
    override var name: String? = null
    override val modifiers: MutableList<KModifier> = mutableListOf()

    fun accessModifier(modifier: KModifier) {
        if(ALL_ACCESS_MODIFIERS.any { it in modifiers }) throw IllegalArgumentException("access modifier already set")

        modifiers.add(modifier)
    }

    fun private() {
        modifiers.add(KModifier.PRIVATE)
    }
}