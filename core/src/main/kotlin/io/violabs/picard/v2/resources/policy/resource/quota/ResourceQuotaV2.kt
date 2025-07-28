package io.violabs.picard.v2.resources.policy.resource.quota

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ResourceQuota sets aggregate quota restrictions enforced per namespace.
 * apiVersion: v1
 *
 * import "k8s.io/api/core/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/policy-resources/resource-quota-v1/
 */
@GeneratedDsl
data class ResourceQuotaV2(
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
     * Spec defines the desired quota.
     * https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: ResourceQuotaSpec? = null,
    /**
     * Status defines the actual enforced quota and its current usage.
     * https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: ResourceQuotaStatus? = null
) : ConfigResource<ResourceQuotaV2.Version, ObjectMeta> {
    interface Version : APIVersion
}