package io.violabs.picard.domain.k8sResources.workload.deployment

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.Strategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate

data class Deployment(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Deployment.Version> {
    interface Version : APIVersion

    data class Spec(
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val minReadySeconds: Int? = null,
        val strategy: Strategy? = null,
        val revisionHistoryLimit: Int? = null,
        val progressDeadlineSeconds: Int? = null,
        val paused: Boolean? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var selector: LabelSelector? = null
            private var template: PodTemplate.Spec? = null
            var replicas: Int? = null
            var minReadySeconds: Int? = null
            private var strategy: Strategy? = null
            var revisionHistoryLimit: Int? = null
            var progressDeadlineSeconds: Int? = null
            private var paused: Boolean? = null

            fun selector(block: LabelSelector.Builder.() -> Unit) {
                selector = LabelSelector.Builder().apply(block).build()
            }

            fun template(block: PodTemplate.Spec.Builder.() -> Unit) {
                template = PodTemplate.Spec.Builder().apply(block).build()
            }

            fun strategy(block: Strategy.Builder.() -> Unit) {
                strategy = Strategy.Builder().apply(block).build()
            }

            fun paused(value: Boolean = true) {
                paused = value
            }

            override fun build(): Spec {
                return Spec(
                    selector = vRequireNotNull(this::selector),
                    template = template,
                    replicas = replicas,
                    minReadySeconds = minReadySeconds,
                    strategy = strategy,
                    revisionHistoryLimit = revisionHistoryLimit,
                    progressDeadlineSeconds = progressDeadlineSeconds,
                    paused = paused
                )
            }
        }
    }

    data class Status(
        val replicas: Int,
        val availableReplicas: Int? = null,
        val readyReplicas: Int? = null,
        val unavailableReplicas: Int? = null,
        val updatedReplicas: Int? = null,
        val collisionCount: Int? = null,
        val conditions: List<Condition>? = null,
        val observedGeneration: Long? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            var replicas: Int? = null
            var availableReplicas: Int? = null
            var readyReplicas: Int? = null
            var unavailableReplicas: Int? = null
            var updatedReplicas: Int? = null
            var collisionCount: Int? = null
            private var conditions: List<Condition>? = null
            var observedGeneration: Long? = null

            fun conditions(block: StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(block)
            }

            override fun build(): Status {
                return Status(
                    replicas = vRequireNotNull(this::replicas),
                    availableReplicas = availableReplicas,
                    readyReplicas = readyReplicas,
                    unavailableReplicas = unavailableReplicas,
                    updatedReplicas = updatedReplicas,
                    collisionCount = collisionCount,
                    conditions = conditions,
                    observedGeneration = observedGeneration
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        Deployment,
        Spec,
        Spec.Builder,
        Status, Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): Deployment {
            return Deployment(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Deployment, Builder>(Builder()) {
        fun deployment(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}