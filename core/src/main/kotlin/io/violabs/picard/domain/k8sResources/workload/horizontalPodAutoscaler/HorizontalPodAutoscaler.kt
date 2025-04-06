package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler

import io.violabs.picard.domain.K8sEnum
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.VersionTarget
import io.violabs.picard.domain.k8sResources.workload.*
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
        val behavior: Behavior? = null,
        @VersionTarget<KAPIVersion.AutoscalingV2>(KAPIVersion.AutoscalingV2::class)
        val metrics: List<Metric.Spec>? = null
    ) : BaseSpec

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
    ) : BaseStatus

    data class Behavior(
        val scaleDown: HPAScaling.Rules? = null,
        val scaleUp: HPAScaling.Rules? = null
    )

    object HPAScaling {
        data class Rules(
            val policies: List<Policy>? = null,
            val selectPolicy: String? = null,
            val stabilizationWindowSeconds: Int? = null
        )

        data class Policy(
            val type: String,
            val value: Int,
            val periodSeconds: Int
        )
    }
}