package io.violabs.picard.v2.resources.workload.pod.container.security

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * AppArmorProfile defines a pod/container's AppArmor settings.
 */
@GeneratedDsl
data class AppArmorProfile(
    /**
     * type indicates which kind of AppArmor profile will be applied.
     * Valid options are:
     *   Localhost - a profile pre-loaded on the node.
     *   RuntimeDefault - the container runtime's default profile.
     *   Unconfined - no AppArmor enforcement.
     */
    val type: Type,
    /**
     * localhostProfile indicates a profile loaded on the node that should be used.
     * The profile must be preconfigured on the node to work.
     * Must match the loaded name of the profile.
     * Must be set if and only if type is "Localhost".
     */
    val localhostProfile: String? = null
) {
    enum class Type {
        Localhost,
        RuntimeDefault,
        Unconfined
    }
}