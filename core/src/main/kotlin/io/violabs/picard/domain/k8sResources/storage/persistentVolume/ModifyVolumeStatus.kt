package io.violabs.picard.domain.k8sResources.storage.persistentVolume

import io.violabs.picard.domain.DSLBuilder

data class ModifyVolumeStatus(
    val status: String,
    val targetVolumeAttributesClassName: String? = null
) {
    enum class Name {
        Pending,
        InProgress,
        Infeasible
    }

    class Builder : DSLBuilder<ModifyVolumeStatus> {
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