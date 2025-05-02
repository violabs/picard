package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.Condition
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ResourceSpecStatusDSLBuilder
import io.violabs.picard.domain.StandardConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.VersionTarget
import io.violabs.picard.domain.k8sResources.workload.*
import io.violabs.picard.domain.k8sResources.workload.metric.Metric
import java.time.LocalDateTime

data class HorizontalPodAutoscaler(
    override val apiVersion: Version = KAPIVersion.AutoscalingV2,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<HorizontalPodAutoscaler.Version> {
    interface Version : APIVersion

    data class Spec(
        val maxReplicas: Int,
        val scaleTargetRef: CrossVersionObjectReference,
        val minReplicas: Int? = null,
        @VersionTarget<KAPIVersion.AutoscalingV1>(KAPIVersion.AutoscalingV1::class)
        val targetCPUUtilizationPercentage: Int? = null,
        @VersionTarget<KAPIVersion.AutoscalingV2>(KAPIVersion.AutoscalingV2::class)
        val behavior: HorizontalPodAutoscalerBehavior? = null,
        @VersionTarget<KAPIVersion.AutoscalingV2>(KAPIVersion.AutoscalingV2::class)
        val metrics: List<Metric.Spec>? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var maxReplicas: Int? = null
            private var scaleTargetRef: CrossVersionObjectReference? = null
            var minReplicas: Int? = null
            var targetCPUUtilizationPercentage: Int? = null
            private var behavior: HorizontalPodAutoscalerBehavior? = null
            private var metrics: List<Metric.Spec>? = null

            fun scaleTargetRef(block: CrossVersionObjectReference.Builder.() -> Unit) {
                scaleTargetRef = CrossVersionObjectReference.Builder().apply(block).build()
            }

            fun behavior(block: HorizontalPodAutoscalerBehavior.Builder.() -> Unit) {
                behavior = HorizontalPodAutoscalerBehavior.Builder().apply(block).build()
            }

            fun metrics(block: Metric.Spec.Group.() -> Unit) {
                metrics = Metric.Spec.Group().apply(block).specs()
            }

            override fun build(): Spec {
                return Spec(
                    maxReplicas = vRequireNotNull(this::maxReplicas),
                    scaleTargetRef = vRequireNotNull(this::scaleTargetRef),
                    minReplicas = minReplicas,
                    targetCPUUtilizationPercentage = targetCPUUtilizationPercentage,
                    behavior = behavior,
                    metrics = metrics
                )
            }
        }
    }

    data class Status(
        val currentReplicas: Int,
        val desiredReplicas: Int,
        val lastScaleTime: LocalDateTime? = null,
        val observedGeneration: Long? = null,
        @VersionTarget<KAPIVersion.AutoscalingV1>(KAPIVersion.AutoscalingV1::class)
        val currentCPUUtilizationPercentage: Int? = null,
        @VersionTarget<KAPIVersion.AutoscalingV2>(KAPIVersion.AutoscalingV2::class)
        val conditions: List<Condition>? = null,
        @VersionTarget<KAPIVersion.AutoscalingV2>(KAPIVersion.AutoscalingV2::class)
        val currentMetrics: List<Metric.Status>? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            var currentReplicas: Int? = null
            var desiredReplicas: Int? = null
            var lastScaleTime: LocalDateTime? = null
            var observedGeneration: Long? = null
            var currentCPUUtilizationPercentage: Int? = null
            private var conditions: List<Condition>? = null
            private var currentMetrics: List<Metric.Status>? = null

            fun conditions(block: StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(block)
            }

            fun currentMetrics(block: Metric.Status.Group.() -> Unit) {
                currentMetrics = Metric.Status.Group().apply(block).metrics()
            }

            override fun build(): Status {
                return Status(
                    currentReplicas = vRequireNotNull(this::currentReplicas),
                    desiredReplicas = vRequireNotNull(this::desiredReplicas),
                    lastScaleTime = lastScaleTime,
                    observedGeneration = observedGeneration,
                    currentCPUUtilizationPercentage = currentCPUUtilizationPercentage,
                    conditions = conditions,
                    currentMetrics = currentMetrics
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        HorizontalPodAutoscaler,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): HorizontalPodAutoscaler {
            return HorizontalPodAutoscaler(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<HorizontalPodAutoscaler, Builder>(Builder()) {
        fun horizontalPodAutoscaler(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}