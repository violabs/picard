package io.violabs.picard.v2.resources.workload.daemon

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DaemonSetStatus represents the current status of a daemon set.
 */
@GeneratedDsl(withListGroup = true)
data class DaemonSetStatus(
    /**
     * numberReady is the number of nodes that should be running the daemon pod
     * and have one or more of the daemon pod running with a Ready Condition.
     */
    val numberReady: Int,
    /**
     * The number of nodes that are running the daemon pod, but are not
     * supposed to run the daemon pod. More info: https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/
     */
    val numberMisscheduled: Int,
    /**
     * The total number of nodes that should be running the daemon pod
     * (including nodes correctly running the daemon pod).
     * More info: https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/
     */
    val desiredNumberScheduled: Int,
    /**
     * The number of nodes that are running at least 1 daemon pod and are
     * supposed to run the daemon pod. More info: https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/
     */
    val currentNumberScheduled: Int,
    /**
     * The number of nodes that should be running the daemon pod and have one
     * or more of the daemon pod running and available (ready for at least spec.minReadySeconds)
     */
    val numberAvailable: Int? = null,
    /**
     * The number of nodes that should be running the daemon pod and have none of
     * the daemon pod running and available (ready for at least spec.minReadySeconds)
     */
    val numberUnavailable: Int? = null,
    /**
     * The total number of nodes that are running updated daemon pod
     */
    val updatedNumberScheduled: Int? = null,
    /**
     * Count of hash collisions for the DaemonSet. The DaemonSet controller uses this
     * field as a collision avoidance mechanism when it needs to create the name for the newest ControllerRevision.
     */
    val collisionCount: Int? = null,
    /**
     * Represents the latest available observations of a DaemonSet's
     * current state.
     */
    val conditions: List<DaemonSetCondition>? = null,
    /**
     * The most recent generation observed by the daemon set controller.
     */
    val observedGeneration: Long? = null
)