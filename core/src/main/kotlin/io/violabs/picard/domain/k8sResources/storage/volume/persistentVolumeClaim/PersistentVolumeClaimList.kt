package io.violabs.picard.domain.k8sResources.storage.volume.persistentVolumeClaim

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PersistentVolumeClaimList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<PersistentVolumeClaim>,
    override val metadata: ListMeta? = null
) : K8sListResource<PersistentVolumeClaimList.Version, PersistentVolumeClaim> {
    interface Version : APIVersion
}