package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class ResourceClaimTemplateList(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val items: List<ResourceClaimTemplate>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<ResourceClaimTemplateList.Version, ResourceClaimTemplate> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ResourceClaimTemplate,
        ResourceClaimTemplate.Builder,
        ResourceClaimTemplate.Group,
        ResourceClaimTemplateList
        >(ResourceClaimTemplate.Group()) {

        override fun build(): ResourceClaimTemplateList {
            return ResourceClaimTemplateList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}