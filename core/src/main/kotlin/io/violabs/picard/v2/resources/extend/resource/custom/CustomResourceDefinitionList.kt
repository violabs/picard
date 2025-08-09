package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.ExtendListResource

@GeneratedDsl
data class CustomResourceDefinitionList(
    @DefaultValue(
        "KAPIVersion.APIExtensionsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.APIExtensionsV1,
    override val items: List<CustomResourceDefinition>,
    override val metadata: ListMeta? = null
) : ExtendListResource<CustomResourceDefinitionList.Version, CustomResourceDefinition> {
    interface Version : APIVersion
}
