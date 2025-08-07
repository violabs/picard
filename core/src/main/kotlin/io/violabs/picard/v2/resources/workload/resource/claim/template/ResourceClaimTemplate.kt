package io.violabs.picard.v2.resources.workload.resource.claim.template

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/workload-resources/resource-claim-template-v1beta2/
 *
 * ResourceClaimTemplate is used to produce ResourceClaim objects.
 *
 * This is an alpha type and requires enabling the DynamicResourceAllocation feature gate.
 *
 * apiVersion: resource.k8s.io/v1beta2
 *
 * import "k8s.io/api/resource/v1beta2"
 */
@GeneratedDsl
data class ResourceClaimTemplate(
    @DefaultValue(
        "KAPIVersion.ResourceV1Beta2",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta2,
    /**
     * Standard object metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Describes the ResourceClaim that is to be generated.
     *
     * This field is immutable. A ResourceClaim will get created by the control plane for a Pod when needed and then not get updated anymore.
     */
    val spec: ResourceClaimTemplateSpec
) : WorkloadResource<ResourceClaimTemplate.Version, ObjectMeta> {
    interface Version : APIVersion
}