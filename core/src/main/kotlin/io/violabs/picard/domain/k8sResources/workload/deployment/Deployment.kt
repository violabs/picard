package io.violabs.picard.domain.k8sResources.workload.deployment

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.Condition
import io.violabs.picard.domain.k8sResources.workload.Strategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate

data class Deployment(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Deployment.Version> {
    data class Spec(
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val minReadySeconds: Int? = null,
        val strategy: Strategy? = null,
        val revisionHistoryLimit: Int? = null,
        val progressDeadlineSeconds: Int? = null,
        val paused: Boolean? = null
    ) : BaseSpec

    data class Status(
        val replicas: Int,
        val availableReplicas: Int? = null,
        val readyReplicas: Int? = null,
        val unavailableReplicas: Int? = null,
        val updatedReplicas: Int? = null,
        val collisionCount: Int? = null,
        val conditions: List<Condition>? = null,
        val observedGeneration: Long? = null
    ) : BaseStatus

    interface Version : APIVersion
}