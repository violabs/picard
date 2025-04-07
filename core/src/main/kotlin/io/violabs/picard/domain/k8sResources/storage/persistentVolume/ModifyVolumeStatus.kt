package io.violabs.picard.domain.k8sResources.storage.persistentVolume

sealed class ModifyVolumeStatus(
    val status: String
) {
    class Pending : ModifyVolumeStatus("Pending")
    class InProgress : ModifyVolumeStatus("InProgress")
    class Infeasible : ModifyVolumeStatus("Infeasible")
}