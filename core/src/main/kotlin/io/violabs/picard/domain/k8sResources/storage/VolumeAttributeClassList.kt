package io.violabs.picard.domain.k8sResources.storage

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class VolumeAttributeClassList(
    override val apiVersion: Version = KAPIVersion.StorageV1Beta1,
    override val items: List<VolumeAttributesClass>,
    override val metadata: ListMeta? = null
) : K8sListResource<VolumeAttributeClassList.Version, VolumeAttributesClass> {
    interface Version : APIVersion
}
