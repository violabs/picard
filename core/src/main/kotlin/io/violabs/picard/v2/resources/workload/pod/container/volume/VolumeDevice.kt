package io.violabs.picard.v2.resources.workload.pod.container.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * volumeDevice describes a mapping of a raw block device within a container.
 */
@GeneratedDsl(withListGroup = true)
data class VolumeDevice(
    /**
     * name must match the name of a persistentVolumeClaim in the pod
     */
    val name: String,
    /**
     * devicePath is the path inside of the container that the device will be mapped to.
     */
    val devicePath: String
)