package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DslBuilder

data class ContainerUser(
    val linux: LinuxContainerUser? = null
) {
    class Builder : DslBuilder<ContainerUser> {
        private var linux: LinuxContainerUser? = null

        fun linux(scope: LinuxContainerUser.Builder.() -> Unit) {
            linux = LinuxContainerUser.Builder().apply(scope).build()
        }

        override fun build(): ContainerUser {
            return ContainerUser(linux)
        }
    }
}