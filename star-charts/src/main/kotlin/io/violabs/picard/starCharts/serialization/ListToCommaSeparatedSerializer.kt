package io.violabs.picard.starCharts.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

class ListToCommaSeparatedSerializer<T> : JsonSerializer<List<T>>() {
    override fun serialize(
        value: List<T>?,
        gen: JsonGenerator,
        p2: SerializerProvider
    ) {
        if (value == null) return

        gen.writeString(value.joinToString(",") { it.toString() })
    }
}