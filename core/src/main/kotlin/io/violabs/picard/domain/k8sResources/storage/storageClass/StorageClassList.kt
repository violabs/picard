package io.violabs.picard.domain.k8sResources.storage.storageClass

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageListResource

data class StorageClassList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<StorageClass>,
    override val metadata: ListMeta? = null
) : StorageListResource<StorageClassList.Version, StorageClass> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        StorageClass,
        StorageClass.Builder,
        StorageClass.Group,
        StorageClassList
        >(StorageClass.Group()) {

        override fun build(): StorageClassList {
            return StorageClassList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}