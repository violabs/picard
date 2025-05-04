package io.violabs.picard.domain.k8sResources.storage.persistentVolume

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.manifest.StorageResource
import java.time.LocalDateTime

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-claim-v1/
 *
 * import "k8s.io/api/core/v1"
 */
data class PersistentVolume(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : StorageResource<PersistentVolume.Version> {
    interface Version : APIVersion

    data class Spec(
        val accessModes: String? = null,
        val capacity: Map<String, Quantity>? = null,
        val claimRef: ObjectReference? = null,
        val mountOptions: List<String>? = null,
        val nodeAffinity: VolumeNodeAffinity? = null,
        val persistentVolumeReclaimPolicy: String? = null,
        val storageClassName: String? = null,
        val volumeAttributesClassName: String? = null,
        val volumeMode: String? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var accessModes: String? = null
            private var capacity: Map<String, Quantity>? = null
            private var claimRef: ObjectReference? = null
            private var mountOptions: List<String>? = null
            private var nodeAffinity: VolumeNodeAffinity? = null
            var persistentVolumeReclaimPolicy: String? = null
            var storageClassName: String? = null
            var volumeAttributesClassName: String? = null
            var volumeMode: String? = null

            fun capacity(vararg capacity: Pair<String, Quantity>) {
                this.capacity = capacity.toMap()
            }

            fun claimRef(block: ObjectReference.Builder.() -> Unit) {
                claimRef = ObjectReference.Builder().apply(block).build()
            }

            fun mountOptions(vararg mountOptions: String) {
                this.mountOptions = mountOptions.toList()
            }

            fun nodeAffinity(block: VolumeNodeAffinity.Builder.() -> Unit) {
                nodeAffinity = VolumeNodeAffinity.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    accessModes = accessModes,
                    capacity = capacity,
                    claimRef = claimRef,
                    mountOptions = mountOptions,
                    nodeAffinity = nodeAffinity,
                    persistentVolumeReclaimPolicy = persistentVolumeReclaimPolicy,
                    storageClassName = storageClassName,
                    volumeAttributesClassName = volumeAttributesClassName,
                    volumeMode = volumeMode
                )
            }
        }
    }

    data class Status(
        val lastPhaseTransitionTime: LocalDateTime? = null,
        val message: String? = null,
        val phase: String? = null,
        val reason: String? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            var lastPhaseTransitionTime: LocalDateTime? = null
            var message: String? = null
            var phase: String? = null
            var reason: String? = null

            override fun build(): Status {
                return Status(
                    lastPhaseTransitionTime = lastPhaseTransitionTime,
                    message = message,
                    phase = phase,
                    reason = reason
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        PersistentVolume,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): PersistentVolume {
            return PersistentVolume(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<PersistentVolume, Builder>(Builder()) {
        fun persistentVolumeItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}