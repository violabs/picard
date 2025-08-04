package io.violabs.picard.v2.resources.workload.pod.container.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * VolumeMountStatus shows status of volume mounts.
 */
@GeneratedDsl
data class VolumeMountStatus(
    /**
     * MountPath corresponds to the original VolumeMount.
     */
    val mountPath: String,
    /**
     * Name corresponds to the name of the original VolumeMount.
     */
    val name: String,
    /**
     * ReadOnly corresponds to the original VolumeMount.
     */
    val readOnly: Boolean? = null,
    /**
     * RecursiveReadOnly must be set to Disabled, Enabled, or unspecified (for non-readonly mounts).
     * An IfPossible value in the original VolumeMount must be translated to
     * Disabled or Enabled, depending on the mount result.
     */
    val recursiveReadOnly: String? = null
)