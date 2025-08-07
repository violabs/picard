package io.violabs.picard.v2.resources.policy.limit

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * LimitRange sets resource usage limits for each kind of resource in a Namespace.
 * apiVersion: v1
 *
 * import "k8s.io/api/core/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/policy-resources/limit-range-v1/
 */
@GeneratedDsl(withListGroup = true)
data class LimitRange(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Spec defines the limits enforced.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: LimitRangeSpec? = null
) : PolicyResource<LimitRange.Version, ObjectMeta> {
    interface Version : APIVersion
}