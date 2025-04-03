package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*
import java.time.LocalDateTime

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-claim-v1/
 *
 * import "k8s.io/api/core/v1"
 */
data class PersistentVolumeClaim(
    override val apiVersion: Version = Version.V1,
    val metadata: ObjectMetadata,
    val spec: Spec,
    val status: Status
) : K8sResource<PersistentVolumeClaim.Version> {
    override val kind: Kind = Kind.CONFIG_MAP

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;
        override fun toString(): String = refString()
    }

    data class Spec(
        val accessModes: String? = null,
        val selector: LabelSelector? = null,
        val resources: VolumeResourceRequirements? = null,
        val storageClassName: String? = null,
        val volumeName: String? = null,
        val volumeMode: String? = null,
        val currentVolumeAttributesClassName: String? = null,
        val modifyVolumeStatus: String? = null
    )

    data class Status(
        val accessModes: String? = null,
        val allocatedResourceStatuses: Map<String, String>? = null,
        val allocatedResources: Map<String, Quantity>? = null,
        val capacity: Map<String, Quantity>? = null,
        val conditions: List<Condition>? = null,
        val modifyVolumeStatus: ModifyVolumeStatus? = null,
        val phase: String? = null
    )

    data class Condition(
        val status: String,
        val type: String,
        val lastProbeTime: LocalDateTime? = null,
        val lastTransitionTime: LocalDateTime? = null,
        val message: String? = null,
        val reason: String? = null
    )

    sealed class ModifyVolumeStatus(
        val status: String
    ) {
        class Pending : ModifyVolumeStatus("Pending")
        class InProgress : ModifyVolumeStatus("InProgress")
        class Infeasible : ModifyVolumeStatus("Infeasible")
    }
}