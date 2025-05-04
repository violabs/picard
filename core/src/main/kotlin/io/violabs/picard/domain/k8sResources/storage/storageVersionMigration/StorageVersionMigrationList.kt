package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageListResource

data class StorageVersionMigrationList(
    override val apiVersion: Version = KAPIVersion.StorageMigrationV1Alpha1,
    override val items: List<StorageVersionMigration>,
    override val metadata: ListMeta? = null
) : StorageListResource<StorageVersionMigrationList.Version, StorageVersionMigration> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        StorageVersionMigration,
        StorageVersionMigration.Builder,
        StorageVersionMigration.Group,
        StorageVersionMigrationList
        >(StorageVersionMigration.Group()) {

        override fun build(): StorageVersionMigrationList {
            return StorageVersionMigrationList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}