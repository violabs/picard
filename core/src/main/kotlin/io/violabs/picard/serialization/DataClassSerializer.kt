package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import io.violabs.picard.domain.Data

class DataClassSerializer<T : Data> : JsonSerializer<T>() {
    override fun serialize(
        value: T?,
        gen: JsonGenerator,
        ser: SerializerProvider
    ) {
        if (value == null) {
            gen.writeNull()
        } else {
            val content = value.content
            gen.writeObject(content)
        }
    }
}