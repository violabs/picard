package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.policy.*
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus
import io.violabs.picard.domain.k8sResources.workload.ServiceCondition

class ValidatingAdmissionPolicy(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ValidatingAdmissionPolicy.Version> {
    interface Version : APIVersion

    data class Spec(
        val auditAnnotations: List<AuditAnnotation>? = null,
        val failurePolicy: String? = null,
        val matchConditions: List<MatchCondition>? = null,
        val matchConstraints: MatchResources? = null,
        val paramKind: ParamKind? = null,
        val validations: List<Validation>? = null,
        val variables: List<Variable>? = null
    ) : BaseSpec

    data class Status(
        val conditions: List<ServiceCondition>? = null,
        val observedGeneration: Long? = null,
        val typeChecking: TypeChecking? = null
    ) : BaseStatus
}