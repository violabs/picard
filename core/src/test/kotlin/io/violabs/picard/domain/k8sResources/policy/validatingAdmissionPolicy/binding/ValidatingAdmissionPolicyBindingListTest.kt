package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingAdmissionPolicyBindingListTest :
    FullBuildSim<ValidatingAdmissionPolicyBindingList, ValidatingAdmissionPolicyBindingList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingAdmissionPolicyBindingListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyBindingList, ValidatingAdmissionPolicyBindingList.Builder> {
                scenario {
                    id = "minimum"
                    given(ValidatingAdmissionPolicyBindingList.Builder()) {
                        items {
                            validatingAdmissionPolicyBindingItem {  }
                        }
                    }
                    expected = ValidatingAdmissionPolicyBindingList(
                        items = listOf(ValidatingAdmissionPolicyBinding())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyBindingList, ValidatingAdmissionPolicyBindingList.Builder> {
                requireNotEmptyScenario("items") {
                    given(ValidatingAdmissionPolicyBindingList.Builder())
                }
            }
    }
}