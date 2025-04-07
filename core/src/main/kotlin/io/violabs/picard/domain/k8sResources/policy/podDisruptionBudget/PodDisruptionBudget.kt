package io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ServiceCondition
import java.time.LocalDateTime

class PodDisruptionBudget(
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

    ) : BaseSpec

    data class Status(
        val currentHealthy: Int,
        val desiredHealthy: Int,
        val disruptionsAllowed: Int,
        val expectedPods: Int,
        val conditions: List<ServiceCondition>? = null,
        val disruptedPods: Map<String, LocalDateTime>? = null,
        val observedGeneration: Long? = null
    ) : BaseSpec
}