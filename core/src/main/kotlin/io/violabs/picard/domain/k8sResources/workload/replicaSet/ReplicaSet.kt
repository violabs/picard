package io.violabs.picard.domain.k8sResources.workload.replicaSet

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.manifest.WorkloadResource

data class ReplicaSet(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : WorkloadResource<ReplicaSet.Version> {
    interface Version : APIVersion

    data class Spec(
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val minReadySeconds: Int? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var selector: LabelSelector? = null
            private var template: PodTemplate.Spec? = null
            var replicas: Int? = null
            var minReadySeconds: Int? = null

            fun selector(block: LabelSelector.Builder.() -> Unit) {
                selector = LabelSelector.Builder().apply(block).build()
            }

            fun template(block: PodTemplate.Spec.Builder.() -> Unit) {
                template = PodTemplate.Spec.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    selector = vRequireNotNull(this::selector),
                    template = template,
                    replicas = replicas,
                    minReadySeconds = minReadySeconds
                )
            }
        }
    }

    data class Status(
        val replicas: Int,
        val availableReplicas: Int? = null,
        val readyReplicas: Int? = null,
        val fullyLabeledReplicas: Int? = null,
        val conditions: List<Condition>? = null,
        val observedGeneration: Long? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            var replicas: Int = 0
            var availableReplicas: Int? = null
            var readyReplicas: Int? = null
            var fullyLabeledReplicas: Int? = null
            private var conditions: List<Condition>? = null
            var observedGeneration: Long? = null

            fun conditions(block: io.violabs.picard.domain.condition.StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(block)
            }

            override fun build(): Status {
                return Status(
                    replicas = replicas,
                    availableReplicas = availableReplicas,
                    readyReplicas = readyReplicas,
                    fullyLabeledReplicas = fullyLabeledReplicas,
                    conditions = conditions,
                    observedGeneration = observedGeneration
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        ReplicaSet,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): ReplicaSet {
            return ReplicaSet(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ReplicaSet, Builder>(Builder()) {
        fun replicaSetItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}