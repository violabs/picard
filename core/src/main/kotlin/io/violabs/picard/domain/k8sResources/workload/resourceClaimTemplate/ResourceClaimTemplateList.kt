package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ResourceClaimTemplateList(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val items: List<ResourceClaimTemplate>,
    override val metadata: ListMeta? = null
) : K8sListResource<ResourceClaimTemplateList.Version, ResourceClaimTemplate> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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