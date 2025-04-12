package io.violabs.picard.domain.k8sResources.workload.pod.volume

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder

data class VolumeDevice(
    val devicePath: String,
    val name: String
) {
    class Builder : DslBuilder<VolumeDevice> {
        var devicePath: String? = null
        var name: String? = null

        override fun build(): VolumeDevice {
            return VolumeDevice(
                vRequireNotNull(this::devicePath),
                vRequireNotNull(this::name)
            )
        }
    }
}