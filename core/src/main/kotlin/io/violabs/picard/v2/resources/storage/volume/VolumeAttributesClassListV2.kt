package io.violabs.picard.v2.resources.storage.volume

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class VolumeAttributesClassListV2(
    @DefaultValue(
        "KAPIVersion.StorageV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1Beta1,
    override val items: List<VolumeAttributesClassV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<VolumeAttributesClassListV2.Version, VolumeAttributesClassV2> {
    interface Version : APIVersion
}
