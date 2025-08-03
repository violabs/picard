package io.violabs.picard.v2.resources.workload.set.replica

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ReplicaSetStatus represents the current status of a ReplicaSet.
 */
@GeneratedDsl
data class ReplicaSetStatus(
    /**
     * Replicas is the most recently observed number of non-terminating pods.
     */
    val replicas: Int,
    /**
     * The number of available non-terminating pods (ready for at least minReadySeconds)
     * for this replica set.
     */
    val availableReplicas: Int? = null,
    /**
     * The number of non-terminating pods targeted by this ReplicaSet with a Ready Condition.
     */
    val readyReplicas: Int? = null,
    /**
     * The number of terminating pods for this replica set. Terminating pods have a
     * non-null .metadata.deletionTimestamp and have not yet reached the Failed or
     * Succeeded .status.phase. This is an alpha field. Enable
     * DeploymentReplicaSetTerminatingReplicas to be able to use this field.
     */
    val terminatingReplicas: Int? = null,
    /**
     * The number of non-terminating pods that have labels matching the labels of the
     * pod template of the replicaset.
     */
    val fullyLabeledReplicas: Int? = null,
    /**
     * Represents the latest available observations of a replica set's current state.
     */
    val conditions: List<ReplicaSetCondition>? = null,
    /**
     * ObservedGeneration reflects the generation of the most recently observed ReplicaSet.
     */
    val observedGeneration: Long? = null
)