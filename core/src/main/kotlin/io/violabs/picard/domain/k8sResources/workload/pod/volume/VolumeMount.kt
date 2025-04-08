package io.violabs.picard.domain.k8sResources.workload.pod.volume

import io.violabs.picard.domain.DslBuilder

data class VolumeMount(
    val name: String,
    val mountPath: String,
    val mountPropagation: String? = null,
    val readOnly: Boolean? = null,
    val recursiveReadOnly: String? = null,
    val subPath: String? = null,
    val subPathExpr: String? = null
) {
    data class Status(
        val mountPath: String,
        val name: String,
        val readOnly: Boolean? = null,
        val recursiveReadOnly: String? = null
    ) {
        class Builder : DslBuilder<Status> {
            var mountPath: String? = null
            var name: String? = null
            var readOnly: Boolean? = null
            var recursiveReadOnly: String? = null

            fun readOnly() {
                readOnly = true
            }

            override fun build(): Status {
                return Status(
                    requireNotNull(mountPath) { "mountPath is required" },
                    requireNotNull(name) { "name is required" },
                    readOnly,
                    recursiveReadOnly
                )
            }
        }
    }

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
                requireNotNull(name) { "name is required" },
                requireNotNull(mountPath) { "mountPath is required" },
                mountPropagation,
                readOnly,
                recursiveReadOnly,
                subPath,
                subPathExpr
            )
        }
    }
}