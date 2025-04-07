package io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CustomResourceDefinitionList(
    override val apiVersion: Version = KAPIVersion.APIExtensionsV1,
    override val items: List<CustomResourceDefinition>,
    override val metadata: ListMeta? = null
) : K8sListResource<CustomResourceDefinitionList.Version, CustomResourceDefinition> {
    interface Version : APIVersion
}
