package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder

data class ContainerUser(
    val linux: LinuxContainerUser? = null
) {
    class Builder : DslBuilder<ContainerUser> {
        var linux: LinuxContainerUser? = null

        override fun build(): ContainerUser {
            return ContainerUser(linux)
        }
    }
}