package io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PersistentVolumeClaimList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<PersistentVolumeClaim>,
    override val metadata: ListMeta? = null
) : K8sListResource<PersistentVolumeClaimList.Version, PersistentVolumeClaim> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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