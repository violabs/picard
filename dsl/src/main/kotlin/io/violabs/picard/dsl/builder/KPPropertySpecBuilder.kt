package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName


@PicardDSLMarker
internal class KPPropertySpecBuilder : TypedSpec, MutabilitySpec, DefaultKotlinPoetSpec() {
    override var type: TypeName? = null
    override var mutable: Boolean = true
    var initializer: String? = null

    fun initNullValue() {
        initializer = "null"
    }

    fun build(): PropertySpec {
        var spec = PropertySpec
            .builder(
                requireNotNull(name) { "name must be set" },
                requireNotNull(type) { "type must be set" },
                *modifiers.toTypedArray()
            )
            .mutable(mutable)

        spec = initializer?.let { spec.initializer(it) } ?: spec

        return spec.build()
    }

    @PicardDSLMarker
    class Group {
        val items: MutableList<PropertySpec> = mutableListOf()

        fun add(block: KPPropertySpecBuilder.() -> Unit) {
            items.add(KPPropertySpecBuilder().apply(block).build())
        }
    }

    companion object {
        internal val ALL_ACCESS_MODIFIERS = listOf(KModifier.PUBLIC, KModifier.PROTECTED, KModifier.INTERNAL, KModifier.PRIVATE)
    }
}