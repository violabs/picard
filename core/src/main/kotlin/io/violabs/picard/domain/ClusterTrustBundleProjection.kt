package io.violabs.picard.domain

data class ClusterTrustBundleProjection(
    val path: String,
    val labelSelector: LabelSelector? = null,
    val name: String? = null,
    val optional: Boolean? = null,
    val signerName: String? = null
)