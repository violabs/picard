package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import io.violabs.picard.common.YAMLMap

class ListAsMapSerializer<T : YAMLMap> : JsonSerializer<List<T>>() {

    override fun serialize(
        value: List<T>?,
        gen: JsonGenerator,
        ser: SerializerProvider
    ) {
        if (value == null) {
            gen.writeNull()
        } else {
            gen.writeStartObject()
            value.forEach {
                gen.writeObjectField(it.key, it.value)
            }
            gen.writeEndObject()
        }
    }
}