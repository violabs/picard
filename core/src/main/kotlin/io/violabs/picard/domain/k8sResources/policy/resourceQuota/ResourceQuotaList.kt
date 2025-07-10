package io.violabs.picard.domain.k8sResources.policy.resourceQuota

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyListResource

data class ResourceQuotaList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ResourceQuota>,
    override val metadata: ListMeta? = null
) : PolicyListResource<ResourceQuotaList.Version, ResourceQuota> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ResourceQuota,
        ResourceQuota.Builder,
        ResourceQuota.Group,
        ResourceQuotaList
        >(ResourceQuota.Group()) {

        override fun build(): ResourceQuotaList {
            return ResourceQuotaList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
