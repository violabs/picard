package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.policy.*

data class ValidatingAdmissionPolicy(
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
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var auditAnnotations: List<AuditAnnotation>? = null
            var failurePolicy: String? = null
            private var matchConditions: List<MatchCondition>? = null
            private var matchConstraints: MatchResources? = null
            private var paramKind: ParamKind? = null
            private var validations: List<Validation>? = null
            private var variables: List<Variable>? = null

            fun auditAnnotations(scope: AuditAnnotation.Group.() -> Unit) {
                auditAnnotations = AuditAnnotation.Group().apply(scope).annotations()
            }

            fun matchConditions(scope: MatchCondition.Group.() -> Unit) {
                matchConditions = MatchCondition.Group().apply(scope).conditions()
            }

            fun matchConstraints(scope: MatchResources.Builder.() -> Unit) {
                matchConstraints = MatchResources.Builder().apply(scope).build()
            }

            fun paramKind(scope: ParamKind.Builder.() -> Unit) {
                paramKind = ParamKind.Builder().apply(scope).build()
            }

            fun validations(scope: Validation.Group.() -> Unit) {
                validations = Validation.Group().apply(scope).validations()
            }

            fun variables(scope: Variable.Group.() -> Unit) {
                variables = Variable.Group().apply(scope).variables()
            }

            override fun build(): Spec {
                return Spec(
                    auditAnnotations = auditAnnotations,
                    failurePolicy = failurePolicy,
                    matchConditions = matchConditions,
                    matchConstraints = matchConstraints,
                    paramKind = paramKind,
                    validations = validations,
                    variables = variables
                )
            }
        }
    }

    data class Status(
        val conditions: List<ServiceCondition>? = null,
        val observedGeneration: Long? = null,
        val typeChecking: TypeChecking? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var conditions: List<ServiceCondition>? = null
            var observedGeneration: Long? = null
            private var typeChecking: TypeChecking? = null

            fun conditions(scope: ServiceConditionGroup.() -> Unit) {
                conditions = ServiceCondition.group(scope)
            }

            fun typeChecking(scope: TypeChecking.Builder.() -> Unit) {
                typeChecking = TypeChecking.Builder().apply(scope).build()
            }

            override fun build(): Status {
                return Status(
                    conditions = conditions,
                    observedGeneration = observedGeneration,
                    typeChecking = typeChecking
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        ValidatingAdmissionPolicy,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): ValidatingAdmissionPolicy {
            return ValidatingAdmissionPolicy(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ValidatingAdmissionPolicy, Builder>(Builder()) {
        fun policy(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}