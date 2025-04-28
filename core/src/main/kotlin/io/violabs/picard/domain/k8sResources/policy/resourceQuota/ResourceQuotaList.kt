package io.violabs.picard.domain.k8sResources.policy.resourceQuota

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ResourceQuotaList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ResourceQuota>,
    override val metadata: ListMeta? = null
) : K8sListResource<ResourceQuotaList.Version, ResourceQuota> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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
