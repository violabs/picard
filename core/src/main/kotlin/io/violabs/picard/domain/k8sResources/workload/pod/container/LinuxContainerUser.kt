package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder

data class LinuxContainerUser(
    val gid: Long,
    val uid: Long,
    val supplementalGroups: List<Long>? = null
) {
    class Builder : DslBuilder<LinuxContainerUser> {
        var gid: Long? = null
        var uid: Long? = null
        private var supplementalGroups: List<Long>? = null

        fun supplementalGroups(vararg groups: Long) {
            supplementalGroups = groups.toList()
        }

        override fun build(): LinuxContainerUser {
            return LinuxContainerUser(
                requireNotNull(gid) { "gid must not be null" },
                requireNotNull(uid) { "uid must not be null" },
                supplementalGroups
            )
        }
    }
}