package io.violabs.picard.v2.resources.workload.set.stateful

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * StatefulSetStatus represents the current state of a StatefulSet.
 */
@GeneratedDsl(withListGroup = true)
data class StatefulSetStatus(
    /**
     * replicas is the number of Pods created by the StatefulSet controller.
     */
    val replicas: Int,
    /**
     * readyReplicas is the number of pods created for this StatefulSet with a Ready Condition.
     */
    val readyReplicas: Int? = null,
    /**
     * currentReplicas is the number of Pods created by the StatefulSet controller
     * from the StatefulSet version indicated by currentRevision.
     */
    val currentReplicas: Int? = null,
    /**
     * updatedReplicas is the number of Pods created by the StatefulSet controller
     * from the StatefulSet version indicated by updateRevision.
     */
    val updatedReplicas: Int? = null,
    /**
     * Total number of available pods (ready for at least minReadySeconds) targeted by this statefulset.
     */
    val availableReplicas: Int? = null,
    /**
     * collisionCount is the count of hash collisions for the StatefulSet. The
     * StatefulSet controller uses this field as a collision avoidance mechanism when
     * it needs to create the name for the newest ControllerRevision.
     */
    val collisionCount: Int? = null,
    /**
     * Represents the latest available observations of a statefulset's current state.
     */
    val conditions: List<StatefulSetCondition>? = null,
    /**
     * currentRevision, if not empty, indicates the version of the StatefulSet used
     * to generate Pods in the sequence [0,currentReplicas).
     */
    val currentRevision: String? = null,
    /**
     * updateRevision, if not empty, indicates the version of the StatefulSet used to
     * generate Pods in the sequence [replicas-updatedReplicas,replicas)
     */
    val updateRevision: String? = null,
    /**
     * observedGeneration is the most recent generation observed for this StatefulSet.
     * It corresponds to the StatefulSet's generation, which is updated on mutation by the API Server.
     */
    val observedGeneration: Long? = null
)