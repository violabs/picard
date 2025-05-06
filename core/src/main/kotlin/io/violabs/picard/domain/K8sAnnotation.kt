package io.violabs.picard.domain

data class K8sAnnotation(
    val key: String,
    val value: String
) {
    class Group {
        private var annotations: MutableList<K8sAnnotation> = mutableListOf()

        fun annotations(): MutableList<K8sAnnotation>? {
            return annotations
        }

        fun annotations(key: String, value: String) {
            annotations.add(K8sAnnotation(key, value))
        }
    }
}