package io.violabs.picard.domain

data class Pod(
    val apiVersion: Version,
    val kind: Kind,
    val metadata: Metadata,
    val spec: Spec
) {
    enum class Version(private val ref: String? = null) {
        BATCH_V1("batch/v1"),
        V1;

        override fun toString(): String = ref ?: name.lowercase()
    }
}