package io.violabs.picard.v2.resources.workload.daemon

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DaemonSetUpdateStrategy is a struct used to control the update strategy for a DaemonSet.
 */
@GeneratedDsl
data class DaemonSetUpdateStrategy(
    /**
     * Type of daemon set update. Can be "RollingUpdate" or "OnDelete". Default is RollingUpdate.
     */
    val type: Type? = null,
    /**
     * Rolling update config params. Present only if type = "RollingUpdate".
     */
    val rollingUpdate: RollingUpdateDaemonSet? = null
) {
    enum class Type {
        /**
         * RollingUpdate indicates that the DaemonSet should be updated using a rolling update strategy.
         */
        RollingUpdate,

        /**
         * OnDelete indicates that the DaemonSet should be updated only when the existing pods are deleted.
         */
        OnDelete
    }
}