package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * AttachedVolume describes a volume attached to a node
 */
@GeneratedDsl(withListGroup = true)
data class AttachedVolume(
    /**
     * DevicePath represents the device path where the volume should be available
     */
    val devicePath: String,
    /**
     * Name of the attached volume
     */
    val name: String
)