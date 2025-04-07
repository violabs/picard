package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.Condition


data class StorageVersionMigration(
    override val apiVersion: Version = KAPIVersion.StorageMigrationV1Alpha1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null,
) : K8sResource<StorageVersionMigration.Version> {
    interface Version : APIVersion

    data class Spec(
        val resource: GroupVersionResource? = null,
        val continueToken: String? = null
    ) : BaseSpec {
        class GroupVersionResource(
            val group: String? = null,
            val resource: String? = null,
            val version: String? = null
        )
    }

    data class Status(
        val conditions: List<Condition>,
        val resourceVersion: String? = null
    ) : BaseStatus
}