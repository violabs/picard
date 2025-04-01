package io.violabs.picard.domain

class PodStatus(
    val conditions: List<PodCondition>,
    val containerStatus: ContainerStatus? = null
)

