package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class StorageVersionMigrationList(
    override val apiVersion: Version = KAPIVersion.StorageMigrationV1Alpha1,
    override val items: List<StorageVersionMigration>,
    override val metadata: ListMeta? = null
) : K8sListResource<StorageVersionMigrationList.Version, StorageVersionMigration> {
    interface Version : APIVersion
}