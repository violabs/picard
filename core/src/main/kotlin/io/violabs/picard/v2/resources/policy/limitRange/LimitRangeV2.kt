package io.violabs.picard.v2.resources.policy.limitRange

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/policy-resources/limit-range-v1/
 *
 * LimitRange sets resource usage limits for each kind of resource in a Namespace.
 * apiVersion: v1
 */
@GeneratedDsl
data class LimitRangeV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired limits to enforce.
     */
    val spec: LimitRangeSpec? = null,
) : PolicyResource<LimitRangeV2.Version, ObjectMeta> {
    interface Version : APIVersion
}
