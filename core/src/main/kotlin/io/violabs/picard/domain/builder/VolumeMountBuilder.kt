package io.violabs.picard.domain.builder

import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount

class VolumeMountBuilder : Builder<VolumeMount> {
    var name: String? = null
    var mountPath: String? = null

    override fun build(): VolumeMount = VolumeMount(
        name = requireNotNull(name) { "Please provide a name" },
        mountPath = requireNotNull(mountPath) { "Please provide a mount path" }
    )
}