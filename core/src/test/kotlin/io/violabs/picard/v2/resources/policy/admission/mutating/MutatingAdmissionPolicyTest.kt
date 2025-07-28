package io.violabs.picard.v2.resources.policy.admission.mutating

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.policy.admission.validating.MatchCondition
import io.violabs.picard.v2.resources.policy.admission.validating.MatchResources
import io.violabs.picard.v2.resources.policy.admission.validating.NamedRuleWithOperations
import io.violabs.picard.v2.resources.policy.admission.validating.ParamKind
import io.violabs.picard.v2.resources.policy.admission.validating.Variable
import org.junit.jupiter.api.BeforeAll

class MutatingAdmissionPolicyTest :
    SuccessBuildSim<MutatingAdmissionPolicyV2, MutatingAdmissionPolicyV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MutatingAdmissionPolicyTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val MATCH_CONDITION = MatchCondition(
            expression = PLACEHOLDER,
            name = PLACEHOLDER
        )

        private val PARAM_KIND = ParamKind(
            apiVersion = PLACEHOLDER,
            kind = PLACEHOLDER
        )

        private val VARIABLE = Variable(
            name = PLACEHOLDER,
            expression = PLACEHOLDER
        )

        private val MUTATION = Mutation(
            patchType = PLACEHOLDER,
            applyConfiguration = ApplyConfiguration(
                expression = PLACEHOLDER
            ),
            jsonPatch = JsonPatch(
                expression = PLACEHOLDER
            )
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<MutatingAdmissionPolicyV2, MutatingAdmissionPolicyV2DslBuilder> {
                scenario {
                    id = "minimum"
                    given(MutatingAdmissionPolicyV2DslBuilder())
                    expected = MutatingAdmissionPolicyV2()
                }

                scenario {
                    id = "full"
                    given(MutatingAdmissionPolicyV2DslBuilder()) {
                        metadata {
                            sharedObjectMeta()
                        }
                        spec {
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
                            mutations {
                                mutation {
                                    patchType = PLACEHOLDER
                                    applyConfiguration {
                                        expression = PLACEHOLDER
                                    }
                                    jsonPatch {
                                        expression = PLACEHOLDER
                                    }
                                }
                            }
                            paramKind {
                                apiVersion = PLACEHOLDER
                                kind = PLACEHOLDER
                            }
                            reinvocationPolicy = PLACEHOLDER
                            variables {
                                variable {
                                    name = PLACEHOLDER
                                    expression = PLACEHOLDER
                                }
                            }
                        }
                    }
                    expected = MutatingAdmissionPolicyV2(
                        metadata = Common.OBJECT_META,
                        spec = MutatingAdmissionPolicySpec(
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
                            mutations = listOf(MUTATION),
                            paramKind = PARAM_KIND,
                            reinvocationPolicy = PLACEHOLDER,
                            variables = listOf(VARIABLE)
                        )
                    )
                }
            }
    }
}