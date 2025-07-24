package io.violabs.picard.v2.resources.storage.version.migration

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/storage-version-migration-v1alpha1/
 *
 * StorageVersionMigration represents a migration of stored data to the latest storage version.
 *
 * apiVersion: storagemigration.k8s.io/v1alpha1
 *
 * import "k8s.io/api/storagemigration/v1alpha1"
 */
@GeneratedDsl
data class StorageVersionMigrationV2(
    @DefaultValue(
        "KAPIVersion.StorageMigrationV1Alpha1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageMigrationV1Alpha1,
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the migration.
     */
    val spec: StorageVersionMigrationSpec? = null,
    /**
     * Status of the migration.
     */
    val status: StorageVersionMigrationStatus? = null
) : ConfigResource<StorageVersionMigrationV2.Version, ObjectMeta> {
    interface Version : APIVersion
}