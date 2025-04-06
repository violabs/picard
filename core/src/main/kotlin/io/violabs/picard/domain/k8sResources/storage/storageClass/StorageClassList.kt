package io.violabs.picard.domain.k8sResources.storage.storageClass

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class StorageClassList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<StorageClass>,
    override val metadata: ListMeta? = null
) : K8sListResource<StorageClassList.Version, StorageClass> {
    interface Version : APIVersion
}