package io.violabs.picard.domain.k8sResources.workload.pod.volume

import io.violabs.picard.domain.DslBuilder

data class VolumeMountStatus(
    val mountPath: String,
    val name: String,
    val readOnly: Boolean? = null,
    val recursiveReadOnly: String? = null
) {
    class Builder : DslBuilder<VolumeMountStatus> {
        var mountPath: String? = null
        var name: String? = null
        var readOnly: Boolean? = null
        var recursiveReadOnly: String? = null

        fun readOnly() {
            readOnly = true
        }

        override fun build(): VolumeMountStatus {
            return VolumeMountStatus(
                requireNotNull(mountPath) { "mountPath is required" },
                requireNotNull(name) { "name is required" },
                readOnly,
                recursiveReadOnly
            )
        }
    }
}