package io.violabs.picard.domain.k8sResources.workload.podTemplate

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PodTemplateList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<PodTemplate>,
    override val metadata: ListMeta? = null
) : K8sListResource<PodTemplateList.Version, PodTemplate> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        PodTemplate,
        PodTemplate.Builder,
        PodTemplate.Group,
        PodTemplateList
        >(PodTemplate.Group()) {

        override fun build(): PodTemplateList {
            return PodTemplateList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}