package io.violabs.picard.v2.resources.workload.deployment

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeploymentStatus is the most recently observed status of the Deployment.
 */
@GeneratedDsl
data class DeploymentStatus(
    /**
     * Total number of non-terminating pods targeted by this deployment (their labels match the selector).
     */
    val replicas: Int? = null,
    /**
     * Total number of available pods (ready for at least minReadySeconds) targeted by this deployment.
     */
    val availableReplicas: Int? = null,
    /**
     * readyReplicas is the number of pods targeted by this Deployment with a Ready Condition.
     */
    val readyReplicas: Int? = null,
    /**
     * Total number of unavailable pods targeted by this deployment. This is the total number of
     * pods that are still required for the deployment to have 100% available capacity. They may
     * either be pods that are running but not yet available or pods that still have not been created.
     */
    val unavailableReplicas: Int? = null,
    /**
     * Total number of non-terminating pods targeted by this deployment that have the desired template spec.
     */
    val updatedReplicas: Int? = null,
    /**
     * Total number of terminating pods targeted by this deployment. Terminating pods have a
     * non-null .metadata.deletionTimestamp and have not yet reached the Failed or Succeeded .status.phase.
     * This is an alpha field. Enable DeploymentReplicaSetTerminatingReplicas to be able to use this field.
     */
    val terminatingReplicas: Int? = null,
    /**
     * Count of hash collisions for the Deployment. The Deployment controller uses this field as a
     * collision avoidance mechanism when it needs to create the name for the newest ReplicaSet.
     */
    val collisionCount: Int? = null,
    /**
     * Represents the latest available observations of a deployment's current state.
     */
    val conditions: List<DeploymentCondition>? = null,
    /**
     * The generation observed by the deployment controller.
     */
    val observedGeneration: Long? = null
)