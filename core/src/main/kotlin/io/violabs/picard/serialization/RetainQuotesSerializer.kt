package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator

class RetainQuotesSerializer : JsonSerializer<String>() {
    override fun serialize(
        value: String?,
        gen: JsonGenerator,
        ser: SerializerProvider
    ) {
        if (gen is YAMLGenerator) {
            gen.disable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            gen.writeString(value)
            gen.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
        } else {
            gen.writeString(value)
        }
    }
}