package io.violabs.picard.domain

data class PodResource(
    val apiVersion: APIVersion,
    val kind: Kind,
    val metadata: ObjectMetadata,
    val spec: Spec,
    val status: PodStatus? = null
) {
    enum class Version(override val ref: String? = null) : APIVersion {
        APPS_V1("apps/v1"),
        BATCH_V1("batch/v1"),
        V1;

        override fun toString(): String = refString()
    }
}