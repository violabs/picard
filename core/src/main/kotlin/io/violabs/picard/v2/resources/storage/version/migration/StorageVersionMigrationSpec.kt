package io.violabs.picard.v2.resources.storage.version.migration

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BaseSpec

/**
 * Spec of the storage version migration.
 */
@GeneratedDsl
data class StorageVersionMigrationSpec(
    /**
     * The resource that is being migrated. The migrator sends requests to the
     * endpoint serving the resource. Immutable.
     */
    val resource: GroupVersionResource,
    /**
     * The token used in the list options to get the next chunk of objects to migrate.
     * When the .status.conditions indicates the migration is "Running", users can use
     * this token to check the progress of the migration.
     */
    val continueToken: String? = null
) : BaseSpec