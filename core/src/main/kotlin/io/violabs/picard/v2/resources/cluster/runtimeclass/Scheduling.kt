package io.violabs.picard.v2.resources.cluster.runtimeclass

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Scheduling specifies the scheduling constraints for nodes supporting a RuntimeClass.
 */
@GeneratedDsl
data class Scheduling(
    /**
     * nodeSelector lists labels that must be present on nodes that support this RuntimeClass.
     * Pods using this RuntimeClass can only be scheduled to a node matched by this selector.
     * The RuntimeClass nodeSelector is merged with a pod's existing nodeSelector.
     * Any conflicts will cause the pod to be rejected in admission.
     */
    val nodeSelector: Map<String, String>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * tolerations are appended (excluding duplicates) to pods running with this RuntimeClass during admission,
     * effectively unioning the set of nodes tolerated by the pod and the RuntimeClass.
     */
    val tolerations: List<Toleration>? = null
)