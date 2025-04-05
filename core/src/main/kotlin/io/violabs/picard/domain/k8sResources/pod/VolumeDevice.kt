package io.violabs.picard.domain.k8sResources.pod

data class VolumeDevice(
    val devicePath: String,
    val name: String
)