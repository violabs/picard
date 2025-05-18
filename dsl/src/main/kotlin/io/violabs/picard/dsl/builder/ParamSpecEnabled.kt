package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec

internal abstract class DefaultParamSpecEnabled : ParamSpecEnabled {
    override var param: ParameterSpec? = null
}

internal interface ParamSpecEnabled {
    var param: ParameterSpec?

    fun param(
        block: KPParameterSpecBuilder.() -> Unit
    ): ParameterSpec {
        return KPParameterSpecBuilder().apply(block).build().also { param = it }
    }

    fun varargParam(
        block: KPParameterSpecBuilder.() -> Unit
    ): ParameterSpec {
        return KPParameterSpecBuilder()
            .apply {
                name = "items"
            }
            .apply(block)
            .apply {
                modifiers.add(KModifier.VARARG)
            }
            .build()
            .also { param = it }
    }
}