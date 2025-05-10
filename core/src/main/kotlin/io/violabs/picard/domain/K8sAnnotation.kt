package io.violabs.picard.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.YAMLMap
import io.violabs.picard.serialization.RetainQuotesSerializer

data class K8sAnnotation(
    override val key: String,
    @JsonSerialize(using = RetainQuotesSerializer::class)
    override val value: String
) : YAMLMap {
    class Group {
        private var annotations: MutableList<K8sAnnotation> = mutableListOf()

        fun annotations(): MutableList<K8sAnnotation>? {
            return annotations
        }

        fun add(key: String, value: String) {
            annotations.add(K8sAnnotation(key, value))
        }
    }
}