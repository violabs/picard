package io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageListResource

data class CSIStorageCapacityList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CSIStorageCapacity>,
    override val metadata: ListMeta? = null
) : StorageListResource<CSIStorageCapacityList.Version, CSIStorageCapacity> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        CSIStorageCapacity,
        CSIStorageCapacity.Builder,
        CSIStorageCapacity.Group,
        CSIStorageCapacityList
        >(CSIStorageCapacity.Group()) {

        override fun build(): CSIStorageCapacityList {
            return CSIStorageCapacityList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}