package io.violabs.picard.domain

data class PodResource(
    val apiVersion: Version,
    val kind: Kind,
    val metadata: Metadata,
    val spec: Spec,
    val status: PodStatus? = null
) {
    enum class Version(private val ref: String? = null) {
        APPS_V1("apps/v1"),
        BATCH_V1("batch/v1"),
        V1;

        override fun toString(): String = ref ?: name.lowercase()
    }
}