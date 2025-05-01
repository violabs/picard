package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.Condition
import io.violabs.picard.domain.k8sResources.K8sListResource


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
        class Builder : DSLBuilder<Spec> {
            private var resource: GroupVersionResource? = null
            var continueToken: String? = null

            fun resource(scope: GroupVersionResource.Builder.() -> Unit) {
                resource = GroupVersionResource.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    resource = resource,
                    continueToken = continueToken
                )
            }
        }
    }

    data class Status(
        val conditions: List<Condition>,
        val resourceVersion: String? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var conditions: List<Condition>? = null
            var resourceVersion: String? = null

            fun conditions(scope: StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(scope)
            }

            override fun build(): Status {
                return Status(
                    conditions = vRequireNotEmpty(this::conditions),
                    resourceVersion = resourceVersion
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        StorageVersionMigration,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): StorageVersionMigration {
            return StorageVersionMigration(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<StorageVersionMigration, Builder>(Builder()) {
        fun migration(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}