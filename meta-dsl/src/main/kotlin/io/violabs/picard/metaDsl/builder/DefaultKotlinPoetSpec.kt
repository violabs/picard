package io.violabs.picard.metaDsl.builder

import com.squareup.kotlinpoet.KModifier
import io.violabs.picard.metaDsl.builder.KPPropertySpecBuilder.Companion.ALL_ACCESS_MODIFIERS

interface KotlinPoetSpec {
    var name: String?
    val modifiers: MutableList<KModifier>
}

abstract class DefaultKotlinPoetSpec : KotlinPoetSpec {
    override var name: String? = null
    override val modifiers: MutableList<KModifier> = mutableListOf()

    /**
     * Add a single access modifier to this spec.
     *
     * @throws IllegalArgumentException if an access modifier has already been set
     */
    fun accessModifier(modifier: KModifier) {
        val existing = modifiers.firstOrNull { it in ALL_ACCESS_MODIFIERS }
        if (existing != null) {
            throw IllegalArgumentException("access modifier already set to $existing")
        }

        modifiers.add(modifier)
    }

    fun private() = accessModifier(KModifier.PRIVATE)

    fun public() = accessModifier(KModifier.PUBLIC)
}