package io.violabs.picard.v2.resources.workload.controller.replication

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate

/**
 * ReplicationControllerSpec is the specification of a replication controller.
 */
@GeneratedDsl
data class ReplicationControllerSpec(
    /**
     * Selector is a label query over pods that should match the Replicas count.
     * If Selector is empty, it is defaulted to the labels present on the Pod template.
     * Label keys and values that must match in order to be controlled by this replication
     * controller, if empty defaulted to labels on Pod template.
     */
    val selector: Map<String, String>? = null,
    /**
     * Template is the object that describes the pod that will be created if insufficient
     * replicas are detected. This takes precedence over a TemplateRef. The only allowed
     * template.spec.restartPolicy value is "Always".
     */
    val template: PodTemplate.Spec? = null,
    /**
     * Replicas is the number of desired replicas. This is a pointer to distinguish between
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