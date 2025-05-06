package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import io.violabs.picard.domain.k8sResources.IntOrString

class IntOrStringSerializer : JsonSerializer<IntOrString>() {
    override fun serialize(
        value: IntOrString?,
        gen: JsonGenerator,
        ser: SerializerProvider
    ) {
        if (value == null) {
            gen.writeNull()
        } else {
            val num = requireNotNull(value.num ?: value.str?.toInt())

            gen.writeNumber(num)
        }
    }
}