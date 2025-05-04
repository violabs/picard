package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.condition.ServiceCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.policy.*
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingAdmissionPolicyTest : SuccessBuildSim<ValidatingAdmissionPolicy, ValidatingAdmissionPolicy.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingAdmissionPolicyTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val AUDIT_ANNOTATION = AuditAnnotation(
            key = PLACEHOLDER,
            valueExpression = PLACEHOLDER
        )

        private val MATCH_CONDITION = MatchCondition(
            expression = PLACEHOLDER,
            name = PLACEHOLDER
        )

        private val PARAM_KIND = ParamKind(
            apiVersion = PLACEHOLDER,
            kind = PLACEHOLDER
        )

        private val VALIDATION = Validation(
            messageExpression = PLACEHOLDER,
            expression = PLACEHOLDER,
            message = PLACEHOLDER,
            reason = PLACEHOLDER
        )

        private val VARIABLE = Variable(
            name = PLACEHOLDER,
            expression = PLACEHOLDER
        )

        private val SERVICE_CONDITION = ServiceCondition(
            status = BooleanType.True,
            type = PLACEHOLDER,
            message = PLACEHOLDER,
            lastTransitionTime = NOW,
            reason = PLACEHOLDER,
            observedGeneration = 1,
        )

        private val TYPE_CHECKING = TypeChecking(
            expressionWarnings = listOf(
                ExpressionWarning(
                    fieldRef = PLACEHOLDER,
                    warning = PLACEHOLDER
                )
            )
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicy, ValidatingAdmissionPolicy.Builder> {
                scenario {
                    id = "minimum"
                    given(ValidatingAdmissionPolicy.Builder())
                    expected = ValidatingAdmissionPolicy()
                }

                scenario {
                    id = "full"
                    given(ValidatingAdmissionPolicy.Builder()) {
                        sharedMetadata()
                        spec {
                            auditAnnotations {
                                annotation {
                                    key = PLACEHOLDER
                                    valueExpression = PLACEHOLDER
                                }
                            }
                            failurePolicy = PLACEHOLDER
                            matchConditions {
                                condition {
                                    expression = PLACEHOLDER
                                    name = PLACEHOLDER
                                }
                            }
                            matchConstraints {
                                sharedMatchResources()
                            }
                            paramKind {
                                apiVersion = PLACEHOLDER
                                kind = PLACEHOLDER
                            }
                            validations {
                                validation {
                                    messageExpression = PLACEHOLDER
                                    expression = PLACEHOLDER
                                    message = PLACEHOLDER
                                    reason = PLACEHOLDER
                                }
                            }
                            variables {
                                variable {
                                    name = PLACEHOLDER
                                    expression = PLACEHOLDER
                                }
                            }
                        }
                        this.status {
                            conditions {
                                condition {
                                    status = BooleanType.True
                                    type = PLACEHOLDER
                                    message = PLACEHOLDER
                                    lastTransitionTime = NOW
                                    reason = PLACEHOLDER
                                    observedGeneration = 1
                                }
                            }
                            observedGeneration = 1
                            typeChecking {
                                expressionWarnings {
                                    warning {
                                        fieldRef = PLACEHOLDER
                                        warning = PLACEHOLDER
                                    }
                                }
                            }
                        }
                    }
                    expected = ValidatingAdmissionPolicy(
                        metadata = METADATA,
                        spec = ValidatingAdmissionPolicy.Spec(
                            auditAnnotations = listOf(AUDIT_ANNOTATION),
                            failurePolicy = PLACEHOLDER,
                            matchConditions = listOf(MATCH_CONDITION),
                            matchConstraints = MATCH_RESOURCES,
                            paramKind = PARAM_KIND,
                            validations = listOf(VALIDATION),
                            variables = listOf(VARIABLE)
                        ),
                        status = ValidatingAdmissionPolicy.Status(
                            conditions = listOf(SERVICE_CONDITION),
                            observedGeneration = 1,
                            typeChecking = TYPE_CHECKING
                        )
                    )
                }
            }
    }
}