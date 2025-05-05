package io.violabs.picard.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import io.violabs.picard.domain.k8sResources.KAPIVersion

class KAPIVersionJsonSerializer : JsonSerializer<KAPIVersion>() {
    override fun serialize(value: KAPIVersion?, gen: JsonGenerator, serializers: SerializerProvider) {
        if (value == null) {
            gen.writeNull()
        } else {
            // Write the custom representation: an empty YAML map '{}'
            gen.writeString(value.refString())
        }
    }
}