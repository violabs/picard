package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*
import java.time.LocalDateTime

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-claim-v1/
 *
 * import "k8s.io/api/core/v1"
 */
data class PersistentVolume(
    override val apiVersion: Version = Version.V1,
    val metadata: ObjectMetadata,
    val spec: Spec,
    val status: Status? = null
) : K8sResource<PersistentVolume.Version> {
    override val kind: Kind = Kind.PERSISTENT_VOLUME_CLAIM

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;
        override fun toString(): String = refString()
    }

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
    )

    data class Status(
        val lastPhaseTransitionTime: LocalDateTime? = null,
        val message: String? = null,
        val phase: String? = null,
        val reason: String? = null
    )

    data class VolumeNodeAffinity(
        val required: NodeSelector? = null,
    )

    data class NodeSelector(
        val nodeSelectorTerms: List<NodeSelectorTerm>
    )

    data class NodeSelectorTerm(
        val matchExpression: List<NodeSelectorRequirement>? = null,
        val matchFields: List<NodeSelectorRequirement>? = null
    )

    sealed class ModifyVolumeStatus(
        val status: String
    ) {
        class Pending : ModifyVolumeStatus("Pending")
        class InProgress : ModifyVolumeStatus("InProgress")
        class Infeasible : ModifyVolumeStatus("Infeasible")
    }
}