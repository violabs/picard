package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration

import io.violabs.picard.common.DslBuilder

data class GroupVersionResource(
    val group: String? = null,
    val resource: String? = null,
    val version: String? = null
) {
    class Builder : DslBuilder<GroupVersionResource> {
        var group: String? = null
        var resource: String? = null
        var version: String? = null

        override fun build(): GroupVersionResource {
            return GroupVersionResource(
                group = group,
                resource = resource,
                version = version
            )
        }
    }
}