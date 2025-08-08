package io.violabs.picard.v2.resources.policy.admission.validating.binding

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.policy.admission.MatchResources
import io.violabs.picard.v2.resources.policy.admission.ParamRef
import io.violabs.picard.v2.resources.policy.admission.validating.NamedRuleWithOperations
import io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicyBindingSpec
import org.junit.jupiter.api.BeforeAll

class ValidatingAdmissionPolicyBindingTest :
    SuccessBuildSim<ValidatingAdmissionPolicyBinding, ValidatingAdmissionPolicyBindingDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingAdmissionPolicyBindingTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val PARAM_REF = ParamRef(
            name = PLACEHOLDER,
            namespace = PLACEHOLDER,
            parameterNotFoundAction = PLACEHOLDER,
            selector = Common.LABEL_SELECTOR
        )

        private val MATCH_RESOURCES = MatchResources(
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
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyBinding, ValidatingAdmissionPolicyBindingDslBuilder> {
                scenario {
                    id = "minimum"
                    given(ValidatingAdmissionPolicyBindingDslBuilder())
                    expected = ValidatingAdmissionPolicyBinding()
                }

                scenario {
                    id = "full"
                    given(ValidatingAdmissionPolicyBindingDslBuilder()) {
                        metadata {
                            sharedObjectMeta()
                        }
                        spec {
                            matchResources {
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
                            paramRef {
                                name = PLACEHOLDER
                                namespace = PLACEHOLDER
                                parameterNotFoundAction = PLACEHOLDER
                                selector {
                                    sharedSelector()
                                }
                            }
                            policyName = PLACEHOLDER
                            validationActions(PLACEHOLDER)
                        }
                    }
                    expected = ValidatingAdmissionPolicyBinding(
                        metadata = Common.OBJECT_META,
                        spec = ValidatingAdmissionPolicyBindingSpec(
                            matchResources = MATCH_RESOURCES,
                            paramRef = PARAM_REF,
                            policyName = PLACEHOLDER,
                            validationActions = listOf(PLACEHOLDER)
                        )
                    )
                }
            }
    }
}