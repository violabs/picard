package io.violabs.picard.v2.resources.workload.controller.replication

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ReplicationControllerStatus represents the current status of a replication controller.
 */
@GeneratedDsl
data class ReplicationControllerStatus(
    /**
     * Replicas is the most recently observed number of replicas.
     */
    val replicas: Int,
    /**
     * The number of available replicas (ready for at least minReadySeconds)
     * for this replication controller.
     */
    val availableReplicas: Int? = null,
    /**
     * The number of ready replicas for this replication controller.
     */
    val readyReplicas: Int? = null,
    /**
     * The number of pods that have labels matching the labels of the pod template
     * of the replication controller.
     */
    val fullyLabeledReplicas: Int? = null,
    /**
     * Represents the latest available observations of a replication controller's current state.
     */
    val conditions: List<ReplicationControllerCondition>? = null,
    /**
     * ObservedGeneration reflects the generation of the most recently observed replication controller.
     */
    val observedGeneration: Long? = null
)