package io.violabs.picard.domain.k8sResources.storage.volume.persistentVolumeClaim

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.storage.volume.ModifyVolumeStatus
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus
import io.violabs.picard.domain.k8sResources.workload.Condition

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-claim-v1/
 *
 * import "k8s.io/api/core/v1"
 */
data class PersistentVolumeClaim(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<PersistentVolumeClaim.Version> {
    interface Version : APIVersion

    data class Spec(
        val accessModes: String? = null,
        val selector: LabelSelector? = null,
        val resources: VolumeResourceRequirements? = null,
        val storageClassName: String? = null,
        val volumeName: String? = null,
        val volumeMode: String? = null,
        val currentVolumeAttributesClassName: String? = null,
        // todo: incorrect?
        val modifyVolumeStatus: String? = null
    ) : BaseSpec

    data class Status(
        val accessModes: String? = null,
        val allocatedResourceStatuses: Map<String, String>? = null,
        val allocatedResources: Map<String, Quantity>? = null,
        val capacity: Map<String, Quantity>? = null,
        val conditions: List<Condition>? = null,
        val modifyVolumeStatus: ModifyVolumeStatus? = null,
        val phase: String? = null
    ) : BaseStatus

    class VolumeSource(
        val claimName: String,
        val readOnly: Boolean? = null
    )
}