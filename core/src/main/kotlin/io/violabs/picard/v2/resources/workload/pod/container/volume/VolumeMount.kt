package io.violabs.picard.v2.resources.workload.pod.container.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * VolumeMount describes a mounting of a Volume within a container.
 */
@GeneratedDsl(withListGroup = true)
data class VolumeMount(
    /**
     * This must match the Name of a Volume.
     */
    val name: String,
    /**
     * Path within the container at which the volume should be mounted. Must not contain ':'.
     */
    val mountPath: String,
    /**
     * Mounted read-only if true, read-write otherwise (false or unspecified).
     * Defaults to false.
     */
    val readOnly: Boolean? = null,
    /**
     * Path within the volume from which the container's volume should be mounted.
     * Defaults to "" (volume's root).
     */
    val subPath: String? = null,
    /**
     * Expanded path within the volume from which the container's volume should be mounted.
     * Behaves similarly to SubPath but environment variable references $(VAR_NAME) are expanded using the container's environment.
     * Defaults to "" (volume's root).
     * SubPathExpr and SubPath are mutually exclusive.
     */
    val subPathExpr: String? = null,
    /**
     * Determines how mounts are propagated from the host to container and the other way around.
     * When not set, MountPropagationNone is used.
     * This field is beta in 1.10.
     */
    val mountPropagation: MountPropagationMode? = null
) {
    enum class MountPropagationMode {
        /**
         * No mount propagation ("private" in Linux terminology).
         */
        None,

        /**
         * Mounts get propagated from the host to the container ("rslave" in Linux terminology).
         */
        HostToContainer,

        /**
         * Mounts get propagated from the host to the container and from the container to the host ("rshared" in Linux terminology).
         */
        Bidirectional
    }
}