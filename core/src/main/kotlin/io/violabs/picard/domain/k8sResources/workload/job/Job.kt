package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.K8sEnum
import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus
import io.violabs.picard.domain.k8sResources.workload.NodeCondition
import io.violabs.picard.domain.k8sResources.workload.pod.Pod
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import java.time.LocalDateTime

class Job(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Job.Version> {
    interface Version : APIVersion

    data class Spec(
        // Replicas
        val template: PodTemplate.Spec? = null,
        val parallelism: Int? = null,
        // Lifecycle
        val completions: Int? = null,
        val completionMode: CompletionMode? = null,
        val backoffLimit: Int? = null,
        val activeDeadlineSeconds: Long? = null,
        val ttlSecondsAfterFinished: Int? = null,
        val suspend: Boolean? = null,
        // Selector
        val selector: LabelSelector? = null,
        val manualSelector: Boolean? = null,
        // Beta level
        val podFailurePolicy: Pod.FailurePolicy? = null,
        val successPolicy: Pod.SuccessPolicy? = null,
        // Alpha level
        val backoffLimitPerIndex: Int? = null,
        val managedBy: String? = null,
        val maxFailedIndexes: Int? = null,
        val podReplacementPolicy: String? = null
    ) : BaseSpec {
        enum class CompletionMode : K8sEnum {
            INDEXED,
            NON_INDEXED;

            override fun toString(): String = properCase()
        }
    }

    data class Status(
        val startTime: LocalDateTime? = null,
        val completionTime: LocalDateTime? = null,
        val active: Int? = null,
        val failed: Int? = null,
        val succeeded: Int? = null,
        val completedIndexes: String? = null,
        val conditions: List<NodeCondition>? = null,
        val uncountedTerminatedPods: UncountedTerminatedPods? = null,
        // Beta level
        val ready: Int? = null,
        // Alpha level
        val failedIndexes: String? = null,
        val terminating: Int? = null
    ) : BaseStatus

    data class UncountedTerminatedPods(
        val failed: List<String>? = null,
        val succeeded: List<String>? = null
    )
}