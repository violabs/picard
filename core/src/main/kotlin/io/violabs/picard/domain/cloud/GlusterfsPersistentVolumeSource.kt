package io.violabs.picard.domain.cloud

data class GlusterfsPersistentVolumeSource(
    val endpoints: String,
    val path: String,
    val endpointsNamespace: String? = null,
    val readOnly: Boolean? = null
)