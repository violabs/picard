package io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageListResource

data class PersistentVolumeClaimList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<PersistentVolumeClaim>,
    override val metadata: ListMeta? = null
) : StorageListResource<PersistentVolumeClaimList.Version, PersistentVolumeClaim> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        PersistentVolumeClaim,
        PersistentVolumeClaim.Builder,
        PersistentVolumeClaim.Group,
        PersistentVolumeClaimList>(PersistentVolumeClaim.Group()) {

        override fun build(): PersistentVolumeClaimList {
            return PersistentVolumeClaimList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}