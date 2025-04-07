package io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolume

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolumeClaim.PersistentVolumeClaim

data class PersistentVolumeList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<PersistentVolumeClaim>,
    override val metadata: ListMeta? = null
) : K8sListResource<PersistentVolumeList.Version, PersistentVolumeClaim> {
    interface Version : APIVersion
}