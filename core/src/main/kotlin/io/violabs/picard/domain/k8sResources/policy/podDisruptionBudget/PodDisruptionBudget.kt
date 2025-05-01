package io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.*
import java.time.LocalDateTime

data class PodDisruptionBudget(
    override val apiVersion: Version = KAPIVersion.PolicyV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<PodDisruptionBudget.Version> {
    interface Version : APIVersion

    data class Spec(
        val maxUnavailable: IntOrString? = null,
        val minAvailable: IntOrString? = null,
        val selector: LabelSelector? = null,
        val unhealthyPodEvictionPolicy: String? = null,
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var maxUnavailable: IntOrString? = null
            private var minAvailable: IntOrString? = null
            private var selector: LabelSelector? = null
            var unhealthyPodEvictionPolicy: String? = null

            fun maxUnavailable(amount: Int) {
                this.maxUnavailable = IntOrString(amount)
            }

            fun maxUnavailable(amount: String) {
                this.maxUnavailable = IntOrString(str = amount)
            }

            fun minAvailable(amount: Int) {
                this.minAvailable = IntOrString(amount)
            }

            fun minAvailable(amount: String) {
                this.minAvailable = IntOrString(str = amount)
            }

            fun selector(scope: LabelSelector.Builder.() -> Unit) {
                this.selector = LabelSelector.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    maxUnavailable = maxUnavailable,
                    minAvailable = minAvailable,
                    selector = selector,
                    unhealthyPodEvictionPolicy = unhealthyPodEvictionPolicy
                )
            }
        }
    }

    data class Status(
        val currentHealthy: Int,
        val desiredHealthy: Int,
        val disruptionsAllowed: Int,
        val expectedPods: Int,
        val conditions: List<ServiceCondition>? = null,
        val disruptedPods: Map<String, LocalDateTime>? = null,
        val observedGeneration: Long? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            var currentHealthy: Int? = null
            var desiredHealthy: Int? = null
            var disruptionsAllowed: Int? = null
            var expectedPods: Int? = null
            private var conditions: List<ServiceCondition>? = null
            private var disruptedPods: Map<String, LocalDateTime>? = null
            var observedGeneration: Long? = null

            fun conditions(scope: ServiceConditionGroup.() -> Unit) {
                this.conditions = ServiceCondition.group(scope)
            }

            fun disruptedPods(vararg pairs: Pair<String, LocalDateTime>) {
                this.disruptedPods = pairs.toMap()
            }

            override fun build(): Status {
                return Status(
                    currentHealthy = vRequireNotNull(this::currentHealthy),
                    desiredHealthy = vRequireNotNull(this::desiredHealthy),
                    disruptionsAllowed = vRequireNotNull(this::disruptionsAllowed),
                    expectedPods = vRequireNotNull(this::expectedPods),
                    conditions = conditions,
                    disruptedPods = disruptedPods,
                    observedGeneration = observedGeneration
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        PodDisruptionBudget,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): PodDisruptionBudget {
            return PodDisruptionBudget(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }
    
    class Group : K8sListResource.ItemGroup<PodDisruptionBudget, Builder>(Builder()) {
        fun budget(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}