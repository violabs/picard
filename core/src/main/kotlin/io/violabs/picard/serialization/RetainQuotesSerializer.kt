package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator

class RetainQuotesSerializer : RetainableStringSerializer<String>() {
    override fun processContent(gen: JsonGenerator, value: String?) {
        value?.let(gen::writeString)
    }
}