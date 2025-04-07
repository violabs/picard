package io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class CSIStorageCapacityList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CSIStorageCapacity>,
    override val metadata: ListMeta? = null
) : K8sListResource<CSIStorageCapacityList.Version, CSIStorageCapacity> {
    interface Version : APIVersion
}