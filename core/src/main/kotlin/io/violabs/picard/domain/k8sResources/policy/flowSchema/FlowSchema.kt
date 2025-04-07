package io.violabs.picard.domain.k8sResources.policy.flowSchema

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.policy.FlowDistinguisherMethod
import io.violabs.picard.domain.k8sResources.policy.PolicyRulesWithSubjects
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationReference
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.Condition

class FlowSchema(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<FlowSchema.Version> {
    interface Version : APIVersion

    data class Spec(
        val priorityLevelConfiguration: PriorityLevelConfigurationReference,
        val distinguisherMethod: FlowDistinguisherMethod? = null,
        val matchingPrecedence: Int? = null,
        val rules: List<PolicyRulesWithSubjects>? = null
    ) : BaseSpec

    data class Status(
        val conditions: List<Condition>
    ) : BaseSpec
}