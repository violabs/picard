package io.violabs.picard.domain.k8sResources.workload.statefulSet

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolumeClaim.PersistentVolumeClaim
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.Condition
import io.violabs.picard.domain.k8sResources.workload.UpdateStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate

data class StatefulSet(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<StatefulSet.Version> {
    interface Version : APIVersion

    data class Spec(
        val serviceName: String,
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val updateStrategy: UpdateStrategy? = null,
        val podManagementPolicy: String? = null,
        val revisionHistoryLimit: Int? = null,
        val volumeClaimTemplates: List<PersistentVolumeClaim>? = null,
        val minReadySeconds: Int? = null,
        val persistentVolumeClaimRetentionPolicy: PersistentVolumeClaimRetentionPolicy? = null,
        val ordinals: Ordinals? = null
    ) : BaseSpec

    data class Status(
        val replicas: Int,
        val readyReplicas: Int? = null,
        val currentReplicas: Int? = null,
        val updatedReplicas: Int? = null,
        val availableReplicas: Int? = null,
        val collisionCount: Int? = null,
        val conditions: List<Condition>? = null,
        val currentRevision: String? = null,
        val updateRevision: String? = null,
        val observedGeneration: Long? = null
    ) : BaseStatus

    data class PersistentVolumeClaimRetentionPolicy(
        val whenDeleted: String? = null,
        val whenScaled: String? = null
    )

    data class Ordinals(
        val start: Int? = null
    )
}