package io.violabs.picard.domain.k8sResources.workload.pod.volume

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class VolumeMount(
    val name: String,
    val mountPath: String,
    val mountPropagation: String? = null,
    val readOnly: Boolean? = null,
    val recursiveReadOnly: String? = null,
    val subPath: String? = null,
    val subPathExpr: String? = null
) {
    class Builder : DslBuilder<VolumeMount> {
        var name: String? = null
        var mountPath: String? = null
        var mountPropagation: String? = null
        var readOnly: Boolean? = null
        var recursiveReadOnly: String? = null
        var subPath: String? = null
        var subPathExpr: String? = null

        fun readOnly() {
            readOnly = true
        }

        override fun build(): VolumeMount {
            return VolumeMount(
                vRequireNotNull(this::name),
                vRequireNotNull(this::mountPath),
                mountPropagation,
                readOnly,
                recursiveReadOnly,
                subPath,
                subPathExpr
            )
        }
    }
}