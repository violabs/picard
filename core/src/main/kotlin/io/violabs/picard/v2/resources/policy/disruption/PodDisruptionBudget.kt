package io.violabs.picard.v2.resources.policy.disruption

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * PodDisruptionBudget is an object to define the max disruption that can be caused to a collection of pods.
 * apiVersion: policy/v1
 *
 * import "k8s.io/api/policy/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/policy-resources/pod-disruption-budget-v1/
 */
@GeneratedDsl(withListGroup = true)
data class PodDisruptionBudget(
    @DefaultValue(
        "KAPIVersion.PolicyV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.PolicyV1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired behavior of the PodDisruptionBudget.
     */
    val spec: PodDisruptionBudgetSpec? = null,
    /**
     * Most recently observed status of the PodDisruptionBudget.
     */
    val status: PodDisruptionBudgetStatus? = null
) : PolicyResource<PodDisruptionBudget.Version, ObjectMeta> {
    interface Version : APIVersion
}