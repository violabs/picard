package io.violabs.picard.v2.resources.workload.pod.container.security

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SeccompProfile defines a pod/container's seccomp profile settings.
 * Only one profile source may be set.
 */
@GeneratedDsl
data class SeccompProfile(
    /**
     * type indicates which kind of seccomp profile will be applied.
     * Valid options are:
     *
     * Localhost - a profile defined in a file on the node should be used.
     * RuntimeDefault - the container runtime default profile should be used.
     * Unconfined - no profile should be applied.
     */
    val type: Type,
    /**
     * localhostProfile indicates a profile defined in a file on the node should be used.
     * The profile must be preconfigured on the node to work.
     * Must be a descending path, relative to the kubelet's configured seccomp profile location.
     * Must be set if type is "Localhost". Must NOT be set for any other type.
     */
    val localhostProfile: String? = null
) {
    enum class Type {
        Localhost,
        RuntimeDefault,
        Unconfined
    }
}