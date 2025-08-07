package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.policy.ParamRef
import io.violabs.picard.possibilities
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
            selector = LABEL_SELECTOR,
            policyName = PLACEHOLDER,
            validationActions = PLACEHOLDER_LIST
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
                        sharedMetadata()
                        spec {
                            matchResources {
                                sharedMatchResources()
                            }
                            paramRef {
                                name = PLACEHOLDER
                                namespace = PLACEHOLDER
                                parameterNotFoundAction = PLACEHOLDER
                                selector { sharedSelector() }
                                policyName = PLACEHOLDER
                                validationActions(PLACEHOLDER)
                            }
                        }
                    }
                    expected = ValidatingAdmissionPolicyBinding(
                        metadata = METADATA,
                        spec = ValidatingAdmissionPolicyBindingSpec(
                            matchResources = MATCH_RESOURCES,
                            paramRef = PARAM_REF
                        )
                    )
                }
            }
    }
}