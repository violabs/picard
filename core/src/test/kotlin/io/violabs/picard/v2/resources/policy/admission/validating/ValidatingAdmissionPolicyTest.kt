package io.violabs.picard.v2.resources.policy.admission.validating

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.policy.admission.MatchCondition
import io.violabs.picard.v2.resources.policy.admission.MatchResources
import io.violabs.picard.v2.resources.policy.admission.Variable
import org.junit.jupiter.api.BeforeAll

class ValidatingAdmissionPolicyTest :
    SuccessBuildSim<ValidatingAdmissionPolicyV2, ValidatingAdmissionPolicyV2DslBuilder>() {
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

        private val CONDITION = ValidatingAdmissionPolicyCondition(
            lastTransitionTime = NOW,
            message = PLACEHOLDER,
            observedGeneration = 1,
            reason = PLACEHOLDER,
            status = PLACEHOLDER,
            type = PLACEHOLDER
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
            possibilities<ValidatingAdmissionPolicyV2, ValidatingAdmissionPolicyV2DslBuilder> {
                scenario {
                    id = "minimum"
                    given(ValidatingAdmissionPolicyV2DslBuilder())
                    expected = ValidatingAdmissionPolicyV2()
                }

                scenario {
                    id = "full"
                    given(ValidatingAdmissionPolicyV2DslBuilder()) {
                        metadata {
                            sharedObjectMeta()
                        }
                        spec {
                            auditAnnotations {
                                auditAnnotation {
                                    key = PLACEHOLDER
                                    valueExpression = PLACEHOLDER
                                }
                            }
                            failurePolicy = PLACEHOLDER
                            matchConditions {
                                matchCondition {
                                    expression = PLACEHOLDER
                                    name = PLACEHOLDER
                                }
                            }
                            matchConstraints {
                                matchPolicy = PLACEHOLDER
                                namespaceSelector {
                                    sharedSelector()
                                }
                                objectSelector {
                                    sharedSelector()
                                }
                                excludeResourceRules {
                                    namedRuleWithOperations {
                                        apiGroups(PLACEHOLDER)
                                        apiVersions(PLACEHOLDER)
                                        operations(PLACEHOLDER)
                                        resourceNames(PLACEHOLDER)
                                        resources(PLACEHOLDER)
                                        scope = PLACEHOLDER
                                    }
                                }
                                resourceRules {
                                    namedRuleWithOperations {
                                        apiGroups(PLACEHOLDER)
                                        apiVersions(PLACEHOLDER)
                                        operations(PLACEHOLDER)
                                        resourceNames(PLACEHOLDER)
                                        resources(PLACEHOLDER)
                                        scope = PLACEHOLDER
                                    }
                                }
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
                        status {
                            conditions {
                                validatingAdmissionPolicyCondition {
                                    lastTransitionTime = NOW
                                    message = PLACEHOLDER
                                    observedGeneration = 1
                                    reason = PLACEHOLDER
                                    status = PLACEHOLDER
                                    type = PLACEHOLDER
                                }
                            }
                            observedGeneration = 1
                            typeChecking {
                                expressionWarnings {
                                    expressionWarning {
                                        fieldRef = PLACEHOLDER
                                        warning = PLACEHOLDER
                                    }
                                }
                            }
                        }
                    }
                    expected = ValidatingAdmissionPolicyV2(
                        metadata = Common.OBJECT_META,
                        spec = ValidatingAdmissionPolicySpec(
                            auditAnnotations = listOf(AUDIT_ANNOTATION),
                            failurePolicy = PLACEHOLDER,
                            matchConditions = listOf(MATCH_CONDITION),
                            matchConstraints = MatchResources(
                                matchPolicy = PLACEHOLDER,
                                namespaceSelector = Common.LABEL_SELECTOR,
                                objectSelector = Common.LABEL_SELECTOR,
                                excludeResourceRules = listOf(
                                    NamedRuleWithOperations(
                                        apiGroups = listOf(PLACEHOLDER),
                                        apiVersions = listOf(PLACEHOLDER),
                                        operations = listOf(PLACEHOLDER),
                                        resourceNames = listOf(PLACEHOLDER),
                                        resources = listOf(PLACEHOLDER),
                                        scope = PLACEHOLDER
                                    )
                                ),
                                resourceRules = listOf(
                                    NamedRuleWithOperations(
                                        apiGroups = listOf(PLACEHOLDER),
                                        apiVersions = listOf(PLACEHOLDER),
                                        operations = listOf(PLACEHOLDER),
                                        resourceNames = listOf(PLACEHOLDER),
                                        resources = listOf(PLACEHOLDER),
                                        scope = PLACEHOLDER
                                    )
                                )
                            ),
                            paramKind = PARAM_KIND,
                            validations = listOf(VALIDATION),
                            variables = listOf(VARIABLE)
                        ),
                        status = ValidatingAdmissionPolicyStatus(
                            conditions = listOf(CONDITION),
                            observedGeneration = 1,
                            typeChecking = TYPE_CHECKING
                        )
                    )
                }
            }
    }
}