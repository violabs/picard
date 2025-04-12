package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.Condition

class PriorityLevelConfiguration(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<PriorityLevelConfiguration.Version> {
    interface Version : APIVersion

    data class Spec(
        val type: Type,
        val exempt: ExemptPriorityLevelConfiguration? = null,
        val limited: LimitedPriorityLevelConfiguration? = null
    ) : BaseSpec

    data class Status(
        val conditions: List<Condition>? = null
    ) : BaseSpec

    enum class Type {
        Exempt,
        Limited
    }
}