package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.NodeCondition
import io.violabs.picard.domain.condition.NodeConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.manifest.WorkloadResource
import java.time.LocalDateTime

data class Job(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : WorkloadResource<Job.Version, ObjectMetadata> {
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
        val podFailurePolicy: PodFailurePolicy? = null,
        val successPolicy: PodSuccessPolicy? = null,
        // Alpha level
        val backoffLimitPerIndex: Int? = null,
        val managedBy: String? = null,
        val maxFailedIndexes: Int? = null,
        val podReplacementPolicy: String? = null
    ) : BaseSpec {
        enum class CompletionMode {
            Indexed,
            NonIndexed
        }

        class Builder : DslBuilder<Spec> {
            private var template: PodTemplate.Spec? = null
            var parallelism: Int? = null
            var completions: Int? = null
            var completionMode: CompletionMode? = null
            var backoffLimit: Int? = null
            var activeDeadlineSeconds: Long? = null
            var ttlSecondsAfterFinished: Int? = null
            private var suspend: Boolean? = null
            private var selector: LabelSelector? = null
            private var manualSelector: Boolean? = null
            private var podFailurePolicy: PodFailurePolicy? = null
            private var successPolicy: PodSuccessPolicy? = null
            var backoffLimitPerIndex: Int? = null
            var managedBy: String? = null
            var maxFailedIndexes: Int? = null
            var podReplacementPolicy: String? = null

            fun template(block: PodTemplate.Spec.Builder.() -> Unit) {
                template = PodTemplate.Spec.Builder().apply(block).build()
            }

            fun suspend(value: Boolean = true) {
                suspend = value
            }

            fun selector(block: LabelSelector.Builder.() -> Unit) {
                selector = LabelSelector.Builder().apply(block).build()
            }

            fun manualSelector(value: Boolean = true) {
                manualSelector = value
            }

            fun podFailurePolicy(block: PodFailurePolicy.Builder.() -> Unit) {
                podFailurePolicy = PodFailurePolicy.Builder().apply(block).build()
            }

            fun successPolicy(block: PodSuccessPolicy.Builder.() -> Unit) {
                successPolicy = PodSuccessPolicy.Builder().apply(block).build()
            }


            override fun build(): Spec {
                return Spec(
                    template = template,
                    parallelism = parallelism,
                    completions = completions,
                    completionMode = completionMode,
                    backoffLimit = backoffLimit,
                    activeDeadlineSeconds = activeDeadlineSeconds,
                    ttlSecondsAfterFinished = ttlSecondsAfterFinished,
                    suspend = suspend,
                    selector = selector,
                    manualSelector = manualSelector,
                    podFailurePolicy = podFailurePolicy,
                    successPolicy = successPolicy,
                    backoffLimitPerIndex = backoffLimitPerIndex,
                    managedBy = managedBy,
                    maxFailedIndexes = maxFailedIndexes,
                    podReplacementPolicy = podReplacementPolicy
                )
            }
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
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            var startTime: LocalDateTime? = null
            var completionTime: LocalDateTime? = null
            var active: Int? = null
            var failed: Int? = null
            var succeeded: Int? = null
            var completedIndexes: String? = null
            private var conditions: List<NodeCondition>? = null
            private var uncountedTerminatedPods: UncountedTerminatedPods? = null
            var ready: Int? = null
            var failedIndexes: String? = null
            var terminating: Int? = null

            fun conditions(block: NodeConditionGroup.() -> Unit) {
                conditions = NodeCondition.group(block)
            }

            fun uncountedTerminatedPods(block: UncountedTerminatedPods.Builder.() -> Unit) {
                uncountedTerminatedPods = UncountedTerminatedPods.Builder().apply(block).build()
            }

            override fun build(): Status {
                return Status(
                    startTime = startTime,
                    completionTime = completionTime,
                    active = active,
                    failed = failed,
                    succeeded = succeeded,
                    completedIndexes = completedIndexes,
                    conditions = conditions,
                    uncountedTerminatedPods = uncountedTerminatedPods,
                    ready = ready,
                    failedIndexes = failedIndexes,
                    terminating = terminating
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        Job,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): Job {
            return Job(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Job, Builder>(Builder()) {
        fun jobItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}