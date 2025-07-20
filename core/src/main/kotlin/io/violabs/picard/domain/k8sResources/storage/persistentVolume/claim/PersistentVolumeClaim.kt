package io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.ModifyVolumeStatus
import io.violabs.picard.domain.k8sResources.storage.volume.VolumeResourceRequirements
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.manifest.StorageResource

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-claim-v1/
 *
 * import "k8s.io/api/core/v1"
 */
data class PersistentVolumeClaim(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : StorageResource<PersistentVolumeClaim.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val accessModes: List<AccessMode>? = null,
        val selector: LabelSelector? = null,
        val resources: VolumeResourceRequirements? = null,
        val storageClassName: String? = null,
        val volumeName: String? = null,
        val volumeMode: String? = null,
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var accessModes: List<AccessMode>? = null
            private var selector: LabelSelector? = null
            private var resources: VolumeResourceRequirements? = null
            var storageClassName: String? = null
            var volumeName: String? = null
            var volumeMode: String? = null

            fun accessModes(vararg accessModes: AccessMode) {
                this.accessModes = accessModes.toList()
            }

            fun selector(block: LabelSelector.Builder.() -> Unit) {
                selector = LabelSelector.Builder().apply(block).build()
            }

            fun resources(block: VolumeResourceRequirements.Builder.() -> Unit) {
                resources = VolumeResourceRequirements.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    accessModes = accessModes,
                    selector = selector,
                    resources = resources,
                    storageClassName = storageClassName,
                    volumeName = volumeName,
                    volumeMode = volumeMode
                )
            }
        }
    }

    data class Status(
        val accessModes: List<AccessMode>? = null,
        val allocatedResourceStatuses: Map<String, String>? = null,
        val allocatedResources: Map<String, Quantity>? = null,
        val capacity: Map<String, Quantity>? = null,
        val conditions: List<Condition>? = null,
        var currentVolumeAttributesClassName: String? = null,
        val modifyVolumeStatus: ModifyVolumeStatus? = null,
        val phase: String? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var accessModes: List<AccessMode>? = null
            private var allocatedResourceStatuses: Map<String, String>? = null
            private var allocatedResources: Map<String, Quantity>? = null
            private var capacity: Map<String, Quantity>? = null
            private var conditions: List<Condition>? = null
            var currentVolumeAttributesClassName: String? = null
            private var modifyVolumeStatus: ModifyVolumeStatus? = null
            var phase: String? = null

            fun accessModes(vararg accessModes: AccessMode) {
                this.accessModes = accessModes.toList()
            }

            fun allocatedResourceStatuses(vararg pairs: Pair<String, String>) {
                allocatedResourceStatuses = pairs.toMap()
            }

            fun allocatedResources(vararg pairs: Pair<String, String>) {
                allocatedResources = pairs.associate { (k, v) -> k to Quantity(v) }
            }

            fun capacity(vararg pairs: Pair<String, String>) {
                capacity = pairs.associate { (k, v) -> k to Quantity(v) }
            }

            fun conditions(scope: io.violabs.picard.domain.condition.StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(scope)
            }

            fun modifyVolumeStatus(scope: ModifyVolumeStatus.Builder.() -> Unit) {
                modifyVolumeStatus = ModifyVolumeStatus.Builder().apply(scope).build()
            }

            override fun build(): Status {
                return Status(
                    accessModes = accessModes,
                    allocatedResourceStatuses = allocatedResourceStatuses,
                    allocatedResources = allocatedResources,
                    capacity = capacity,
                    conditions = conditions,
                    currentVolumeAttributesClassName = currentVolumeAttributesClassName,
                    modifyVolumeStatus = modifyVolumeStatus,
                    phase = phase
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        PersistentVolumeClaim,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): PersistentVolumeClaim {
            return PersistentVolumeClaim(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<PersistentVolumeClaim, Builder>(Builder()) {
        fun claims(): List<PersistentVolumeClaim>? = items()

        fun persistentVolumeClaimItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}