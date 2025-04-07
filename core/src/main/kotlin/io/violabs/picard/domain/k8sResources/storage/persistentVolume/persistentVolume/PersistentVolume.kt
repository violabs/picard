package io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolume

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.*
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
) : K8sResource<PersistentVolume.Version> {
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
    ) : BaseSpec

    data class Status(
        val lastPhaseTransitionTime: LocalDateTime? = null,
        val message: String? = null,
        val phase: String? = null,
        val reason: String? = null
    ) : BaseStatus

    data class VolumeNodeAffinity(
        val required: NodeSelector? = null,
    ) : BaseNodeAffinity
}