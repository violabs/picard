package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator

class RetainQuotesMapSerializer : RetainableStringSerializer<Map<String, String>>() {
    override fun processContent(gen: JsonGenerator, value: Map<String, String>?) {
        if (value == null) {
            gen.writeNull()
            return
        }
        gen.writeStartObject()
        value.forEach { (key, v) ->
            gen.writeStringField(key, v)
        }
        gen.writeEndObject()
    }
}
