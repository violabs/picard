package io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendListResource

data class CustomResourceDefinitionList(
    override val apiVersion: Version = KAPIVersion.APIExtensionsV1,
    override val items: List<CustomResourceDefinition>,
    override val metadata: ListMeta? = null
) : ExtendListResource<CustomResourceDefinitionList.Version, CustomResourceDefinition> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        CustomResourceDefinition,
        CustomResourceDefinition.Builder,
        CustomResourceDefinition.Group,
        CustomResourceDefinitionList
        >(CustomResourceDefinition.Group()) {

        override fun build(): CustomResourceDefinitionList {
            return CustomResourceDefinitionList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
