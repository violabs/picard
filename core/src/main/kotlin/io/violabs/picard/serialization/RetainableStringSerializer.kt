package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator

abstract class RetainableStringSerializer<T> : JsonSerializer<T>() {

    override fun serialize(
        value: T?,
        gen: JsonGenerator,
        ser: SerializerProvider
    ) {
        if (gen is YAMLGenerator) {
            yamlProcessor(gen) { processContent(gen, value) }
        } else {
            processContent(gen, value)
        }
    }

    private fun yamlProcessor(gen: YAMLGenerator, block: YAMLGenerator.() -> Unit) {
        gen.disable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
        gen.block()
        gen.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
    }

    abstract fun processContent(gen: JsonGenerator, value: T? = null)
}