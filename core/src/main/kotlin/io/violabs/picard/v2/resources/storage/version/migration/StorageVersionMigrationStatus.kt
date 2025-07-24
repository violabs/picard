package io.violabs.picard.v2.resources.storage.version.migration

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Status of the storage version migration.
 */
@GeneratedDsl
data class StorageVersionMigrationStatus(
    /**
     * Patch strategy: merge on key type
     *
     * Map: unique values on key type will be kept during a merge
     *
     * The latest available observations of the migration's current state.
     *
     * Describes the state of a migration at a certain point.
     */
    val conditions: List<MigrationCondition>? = null,
    /**
     * ResourceVersion to compare with the GC cache for performing the migration. This is the
     * current resource version of given group, version and resource when kube-controller-manager
     * first observes this StorageVersionMigration resource.
     */
    val resourceVersion: String? = null
)