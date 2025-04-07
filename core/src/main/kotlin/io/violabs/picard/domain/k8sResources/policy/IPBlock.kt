package io.violabs.picard.domain.k8sResources.policy

data class IPBlock(
    val cidr: String,
    val except: List<String>? = null
)