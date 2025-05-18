package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName

@PicardDSLMarker
internal class KPParameterSpecBuilder : TypedSpec, DefaultKotlinPoetSpec() {
    override var type: TypeName? = null
    private var defaultValue: String? = null

    fun defaultValue(value: Any?) {
        defaultValue = value?.toString()
    }

    fun build(): ParameterSpec {
        var spec = ParameterSpec.Companion
            .builder(
                requireNotNull(name) { "name must be set" },
                requireNotNull(type) { "type must be set" },
                *modifiers.toTypedArray()
            )

        if (defaultValue != null) spec = spec.defaultValue(defaultValue!!)

        return spec.build()
    }
}