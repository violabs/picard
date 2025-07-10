package io.violabs.picard.domain.k8sResources.workload.pod.volume

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class VolumeMountStatus(
    val mountPath: String,
    val name: String,
    val readOnly: Boolean? = null,
    val recursiveReadOnly: String? = null
) {
    class Builder : DslBuilder<VolumeMountStatus> {
        var mountPath: String? = null
        var name: String? = null
        private var readOnly: Boolean? = null
        var recursiveReadOnly: String? = null

        fun readOnly(value: Boolean = true) {
            readOnly = value
        }

        override fun build(): VolumeMountStatus {
            return VolumeMountStatus(
                vRequireNotNull(this::mountPath),
                vRequireNotNull(this::name),
                readOnly,
                recursiveReadOnly
            )
        }
    }
}