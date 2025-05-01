package io.violabs.picard.domain.k8sResources.storage.persistentVolume

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class PersistentVolumeList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<PersistentVolume>,
    override val metadata: ListMeta? = null
) : K8sListResource<PersistentVolumeList.Version, PersistentVolume> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        PersistentVolume,
        PersistentVolume.Builder,
        PersistentVolume.Group,
        PersistentVolumeList
        >(PersistentVolume.Group()) {

        override fun build(): PersistentVolumeList {
            return PersistentVolumeList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}