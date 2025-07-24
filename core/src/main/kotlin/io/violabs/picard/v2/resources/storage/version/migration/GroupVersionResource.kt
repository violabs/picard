package io.violabs.picard.v2.resources.storage.version.migration

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * The names of the group, the version, and the resource.
 */
@GeneratedDsl
data class GroupVersionResource(
    /**
     * The name of the group.
     */
    val group: String? = null,
    /**
     * The name of the resource.
     */
    val version: String? = null,
    /**
     * The name of the version.
     */
    val resource: String? = null
)