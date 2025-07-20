package io.violabs.picard.domain.k8sResources.storage.persistentVolume

import io.violabs.picard.common.DslBuilder

data class ModifyVolumeStatus(
    val status: String,
    val targetVolumeAttributesClassName: String? = null
) {
    enum class Name {
        Pending,
        InProgress,
        Infeasible
    }

    class Builder : DslBuilder<ModifyVolumeStatus> {
        var status: Name? = null
        var targetVolumeAttributesClassName: String? = null

        override fun build(): ModifyVolumeStatus {
            return ModifyVolumeStatus(
                status = requireNotNull(status?.name) { "status is required" },
                targetVolumeAttributesClassName = targetVolumeAttributesClassName
            )
        }
    }
}