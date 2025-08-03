package io.violabs.picard.v2.resources.workload.pod.container

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class ContainerStatus(
    val name: String,
    val ready: Boolean = false,
    val restartCount: Int = 0,
    val image: String? = null,
    val imageID: String? = null,
    val containerID: String? = null,
    val started: Boolean? = null,
    val state: ContainerState? = null,
    val lastState: ContainerState? = null
)