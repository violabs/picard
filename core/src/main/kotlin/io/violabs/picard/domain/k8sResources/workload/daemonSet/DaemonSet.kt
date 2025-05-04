package io.violabs.picard.domain.k8sResources.workload.daemonSet

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.workload.UpdateStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.domain.label.LabelSelector

data class DaemonSet(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<DaemonSet.Version> {
    interface Version : APIVersion

    data class Spec(
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val minReadySeconds: Int? = null,
        val updateStrategy: UpdateStrategy? = null,
        val revisionHistoryLimit: Int? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var selector: LabelSelector? = null
            private var template: PodTemplate.Spec? = null
            var minReadySeconds: Int? = null
            private var updateStrategy: UpdateStrategy? = null
            var revisionHistoryLimit: Int? = null

            fun selector(block: LabelSelector.Builder.() -> Unit) {
                selector = LabelSelector.Builder().apply(block).build()
            }

            fun template(block: PodTemplate.Spec.Builder.() -> Unit) {
                template = PodTemplate.Spec.Builder().apply(block).build()
            }

            fun updateStrategy(block: UpdateStrategy.Builder.() -> Unit) {
                updateStrategy = UpdateStrategy.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    selector = vRequireNotNull(this::selector),
                    template = template,
                    minReadySeconds = minReadySeconds,
                    updateStrategy = updateStrategy,
                    revisionHistoryLimit = revisionHistoryLimit
                )
            }
        }
    }

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
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            var numberReady: Int = 0
            var numberAvailable: Int? = null
            var numberUnavailable: Int? = null
            var numberMisscheduled: Int? = null
            var desiredNumberScheduled: Int? = null
            var currentNumberScheduled: Int? = null
            var updatedNumberScheduled: Int? = null
            var collisionCount: Int? = null
            private var conditions: List<Condition>? = null
            var observedGeneration: Long? = null

            fun conditions(block: io.violabs.picard.domain.condition.StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(block)
            }

            override fun build(): Status {
                return Status(
                    numberReady = numberReady,
                    numberAvailable = numberAvailable,
                    numberUnavailable = numberUnavailable,
                    numberMisscheduled = numberMisscheduled,
                    desiredNumberScheduled = desiredNumberScheduled,
                    currentNumberScheduled = currentNumberScheduled,
                    updatedNumberScheduled = updatedNumberScheduled,
                    collisionCount = collisionCount,
                    conditions = conditions,
                    observedGeneration = observedGeneration
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        DaemonSet,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): DaemonSet {
            return DaemonSet(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<DaemonSet, Builder>(Builder()) {
        fun daemonSet(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}