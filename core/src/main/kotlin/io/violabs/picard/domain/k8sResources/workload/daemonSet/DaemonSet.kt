package io.violabs.picard.domain.k8sResources.workload.daemonSet

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.Condition
import io.violabs.picard.domain.k8sResources.workload.UpdateStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate

data class DaemonSet(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<DaemonSet.Version> {

    data class Spec(
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val minReadySeconds: Int? = null,
        val updateStrategy: UpdateStrategy? = null,
        val revisionHistoryLimit: Int? = null
    ) : BaseSpec

    data class Status(
        val numberReady: Int,
        val numberAvailable: Int? = null,
        val numberUnavailable: Int? = null,
        val numberMisscheduled: Int? = null,
        val desiredNumberScheduled: Int? = null,
        val currentNumberScheduled: Int? = null,
        val updatedNumberScheduled: Int? = null,
        val collisionCount: Int? = null,
        val conditions: List<Condition>? = null,
        val observedGeneration: Long? = null
    ) : BaseStatus

    interface Version : APIVersion
}