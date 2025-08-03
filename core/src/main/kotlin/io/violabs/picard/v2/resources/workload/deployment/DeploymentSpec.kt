package io.violabs.picard.v2.resources.workload.deployment

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.v2.common.LabelSelector

/**
 * DeploymentSpec is the specification of the desired behavior of the Deployment.
 */
@GeneratedDsl
data class DeploymentSpec(
    /**
     * Label selector for pods. Existing ReplicaSets whose pods are selected by this will be
     * the ones affected by this deployment. It must match the pod template's labels.
     */
    val selector: LabelSelector,
    /**
     * Template describes the pods that will be created. The only allowed template.spec.restartPolicy
     * value is "Always".
     */
    val template: PodTemplate.Spec,
    /**
     * Number of desired pods. This is a pointer to distinguish between explicit zero and not
     * specified. Defaults to 1.
     */
    val replicas: Int? = null,
    /**
     * Minimum number of seconds for which a newly created pod should be ready without any of
     * its container crashing, for it to be considered available. Defaults to 0 (pod will be
     * considered available as soon as it is ready)
     */
    val minReadySeconds: Int? = null,
    /**
     * The deployment strategy to use to replace existing pods with new ones.
     */
    val strategy: DeploymentStrategy? = null,
    /**
     * The number of old ReplicaSets to retain to allow rollback. This is a pointer to distinguish
     * between explicit zero and not specified. Defaults to 10.
     */
    val revisionHistoryLimit: Int? = null,
    /**
     * The maximum time in seconds for a deployment to make progress before it is considered to be
     * failed. The deployment controller will continue to process failed deployments and a condition
     * with a ProgressDeadlineExceeded reason will be surfaced in the deployment status. Note that
     * progress will not be estimated during the time a deployment is paused. Defaults to 600s.
     */
    val progressDeadlineSeconds: Int? = null,
    /**
     * Indicates that the deployment is paused.
     */
    val paused: Boolean? = null
)