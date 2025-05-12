package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator
import io.violabs.picard.common.YAMLMap

class ListAsRetainedQuotesValueMapSerializer<T : YAMLMap> : RetainableStringSerializer<List<T>>() {
    override fun processContent(gen: JsonGenerator, value: List<T>?) {
        gen.writeStartObject()
        value?.forEach {
            gen.writeObjectField(it.key, it.value)
        }
        gen.writeEndObject()
    }
}