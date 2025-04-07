package io.violabs.picard.domain.k8sResources.extend.customResource

data class CustomResourceSubresources(
    val scale: CustomResourceSubresourceScale? = null,
    val status: CustomResourceSubresourceStatus? = null
)