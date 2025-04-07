package io.violabs.picard.domain.k8sResources.policy

data class QueuingConfiguration(
    val handSize: Int? = null,
    val queueLengthLimit: Int? = null,
    val queues: Int? = null
)