package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*
import java.time.LocalDateTime


data class StorageVersionMigration(
    override val apiVersion: Version = Version.STORAGE_MIGRATION_V1_ALPHA_1,
    val metadata: ObjectMetadata,

) : K8sResource<StorageVersionMigration.Version> {
    override val kind: Kind = Kind.STORAGE_VERSION_MIGRATION

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_MIGRATION_V1_ALPHA_1("storagemigration.k8s.io/v1alpha1");
        override fun toString(): String = refString()
    }

    data class Spec(
        val resource: GroupVersionResource? = null,
        val continueToken: String? = null
    ) {
        class GroupVersionResource(
            val group: String? = null,
            val resource: String? = null,
            val version: String? = null
        )
    }

    data class Status(
        val conditions: List<MigrationCondition>,
        val resourceVersion: String? = null
    ) {
        class MigrationCondition(
            val status: Status,
            val type: String,
            val lastUpdateTime: LocalDateTime? = null,
            val message: String? = null,
            val reason: String? = null
        ) {
            enum class Status : K8sEnum {
                TRUE, FALSE, UNKNOWN;

                override fun toString(): String = properCase()
            }
        }
    }
}