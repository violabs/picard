package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ResourceClaimList(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val items: List<ResourceClaim>,
    override val metadata: ListMeta? = null
) : K8sListResource<ResourceClaimList.Version, ResourceClaim> {
    interface Version : APIVersion
}