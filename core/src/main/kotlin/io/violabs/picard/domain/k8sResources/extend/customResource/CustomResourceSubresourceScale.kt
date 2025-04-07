package io.violabs.picard.domain.k8sResources.extend.customResource

data class CustomResourceSubresourceScale(
    val specReplicasPath: String,
    val statusReplicasPath: String,
    val labelSelectorPath: String? = null
)