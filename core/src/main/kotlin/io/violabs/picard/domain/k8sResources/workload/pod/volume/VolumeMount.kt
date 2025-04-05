package io.violabs.picard.domain.k8sResources.workload.pod.volume

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
    )
}