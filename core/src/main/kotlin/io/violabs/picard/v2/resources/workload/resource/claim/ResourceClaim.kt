package io.violabs.picard.v2.resources.workload.resource.claim

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ResourceClaim describes a request for access to resources in the cluster, for use by workloads.
 * For example, if a workload needs an accelerator device with specific properties, this is how that request is expressed. 
 * The status stanza tracks whether this claim has been satisfied and what specific resources have been allocated.
 * 
 * This is an alpha type and requires enabling the DynamicResourceAllocation feature gate.
 * 
 * apiVersion: resource.k8s.io/v1beta2
 * 
 * import "k8s.io/api/resource/v1beta2"
 */
@GeneratedDsl(withListGroup = true)
data class ResourceClaim(
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
     * Spec describes what is being requested and how to configure it. The spec is immutable.
     */
    val spec: ResourceClaimSpec,
    /**
     * Status describes whether the claim is ready to use and what has been allocated.
     */
    val status: ResourceClaimStatus? = null
) : WorkloadResource<ResourceClaim.Version, ObjectMeta> {
    interface Version : APIVersion
}