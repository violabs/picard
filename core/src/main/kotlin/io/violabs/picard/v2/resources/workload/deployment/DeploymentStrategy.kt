package io.violabs.picard.v2.resources.workload.deployment

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeploymentStrategy describes how to replace existing pods with new ones.
 */
@GeneratedDsl
data class DeploymentStrategy(
    /**
     * Type of deployment. Can be "Recreate" or "RollingUpdate". Default is RollingUpdate.
     */
    val type: String? = null,
    /**
     * Rolling update config params. Present only if DeploymentStrategyType = RollingUpdate.
     */
    val rollingUpdate: RollingUpdateDeployment? = null
)