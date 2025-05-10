package io.violabs.picard.domain

import io.violabs.picard.common.YAMLMap

data class K8sAnnotation(
    override val key: String,
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