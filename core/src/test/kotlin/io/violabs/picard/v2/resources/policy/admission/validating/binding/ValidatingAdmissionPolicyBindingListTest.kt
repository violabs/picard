package io.violabs.picard.v2.resources.policy.admission.validating.binding

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingAdmissionPolicyBindingListTest :
    FullBuildSim<ValidatingAdmissionPolicyBindingList, ValidatingAdmissionPolicyBindingListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingAdmissionPolicyBindingListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyBindingList, ValidatingAdmissionPolicyBindingListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(ValidatingAdmissionPolicyBindingListDslBuilder()) {
                        items {
                            validatingAdmissionPolicyBinding { }
                        }
                    }
                    expected = ValidatingAdmissionPolicyBindingList(
                        items = listOf(ValidatingAdmissionPolicyBinding())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyBindingList, ValidatingAdmissionPolicyBindingListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(ValidatingAdmissionPolicyBindingListDslBuilder())
                }
            }
    }
}