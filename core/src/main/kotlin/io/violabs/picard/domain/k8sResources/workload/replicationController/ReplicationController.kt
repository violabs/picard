package io.violabs.picard.domain.k8sResources.workload.replicationController

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.condition.StandardConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate

data class ReplicationController(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ReplicationController.Version> {
    interface Version : APIVersion

    data class Spec(
        val selector: Map<String, String>? = null,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val minReadySeconds: Int? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var selector: Map<String, String>? = null
            private var template: PodTemplate.Spec? = null
            var replicas: Int? = null
            var minReadySeconds: Int? = null

            fun selector(vararg values: Pair<String, String>) {
                selector = values.toMap()
            }

            fun template(block: PodTemplate.Spec.Builder.() -> Unit) {
                template = PodTemplate.Spec.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    selector = selector,
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
            var replicas: Int? = null
            var availableReplicas: Int? = null
            var readyReplicas: Int? = null
            var fullyLabeledReplicas: Int? = null
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
                    fullyLabeledReplicas = fullyLabeledReplicas,
                    conditions = conditions,
                    observedGeneration = observedGeneration
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        ReplicationController,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): ReplicationController {
            return ReplicationController(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ReplicationController, Builder>(Builder()) {
        fun controller(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}