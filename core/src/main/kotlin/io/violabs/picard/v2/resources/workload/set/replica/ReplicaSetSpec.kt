package io.violabs.picard.v2.resources.workload.set.replica

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.v2.common.LabelSelector

/**
 * ReplicaSetSpec is the specification of a ReplicaSet.
 */
@GeneratedDsl
data class ReplicaSetSpec(
    /**
     * Selector is a label query over pods that should match the replica count.
     * Label keys and values that must match in order to be controlled by this replica set.
     * It must match the pod template's labels.
     */
    val selector: LabelSelector,
    /**
     * Template is the object that describes the pod that will be created if insufficient
     * replicas are detected.
     */
    val template: PodTemplate.Spec? = null,
    /**
     * Replicas is the number of desired pods. This is a pointer to distinguish between
     * explicit zero and unspecified. Defaults to 1.
     */
    val replicas: Int? = null,
    /**
     * Minimum number of seconds for which a newly created pod should be ready without
     * any of its container crashing, for it to be considered available. Defaults to 0
     * (pod will be considered available as soon as it is ready)
     */
    val minReadySeconds: Int? = null
)