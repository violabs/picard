package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim

data class ResourceClaimTemplate(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
) : K8sResource<ResourceClaimTemplate.Version> {
    interface Version : APIVersion

    data class Spec(
        val spec: ResourceClaim.Spec,
        val metadata: ObjectMetadata? = null
    ) : BaseSpec
}