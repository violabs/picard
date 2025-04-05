package io.violabs.picard.domain.k8sResources.workload.pod.volume

data class VolumeDevice(
    val devicePath: String,
    val name: String
)