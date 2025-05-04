package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class ResourceClaimList(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val items: List<ResourceClaim>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<ResourceClaimList.Version, ResourceClaim> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        ResourceClaim,
        ResourceClaim.Builder,
        ResourceClaim.Group,
        ResourceClaimList>(ResourceClaim.Group()) {

        override fun build(): ResourceClaimList {
            return ResourceClaimList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}