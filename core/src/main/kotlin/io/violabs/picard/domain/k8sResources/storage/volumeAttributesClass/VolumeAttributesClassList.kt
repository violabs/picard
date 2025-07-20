package io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageListResource

data class VolumeAttributesClassList(
    override val apiVersion: Version = KAPIVersion.StorageV1Beta1,
    override val items: List<VolumeAttributesClass>,
    override val metadata: ListMeta? = null
) : StorageListResource<VolumeAttributesClassList.Version, VolumeAttributesClass> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        VolumeAttributesClass,
        VolumeAttributesClass.Builder,
        VolumeAttributesClass.Group,
        VolumeAttributesClassList
        >(VolumeAttributesClass.Group()) {

        override fun build(): VolumeAttributesClassList {
            return VolumeAttributesClassList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
