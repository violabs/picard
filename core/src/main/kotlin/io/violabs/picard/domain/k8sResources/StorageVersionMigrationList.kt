package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta

data class StorageVersionMigrationList(
    override val apiVersion: Version = Version.STORAGE_MIGRATION_V1_ALPHA_1,
    val metadata: ListMeta,
    val items: List<StorageVersionMigration>
) : K8sResource<StorageVersionMigrationList.Version> {
    override val kind: Kind = Kind.STORAGE_CLASS_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_MIGRATION_V1_ALPHA_1("storage.k8s.io/v1");

        override fun toString(): String = refString()
    }
}