package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingAdmissionPolicyListTest :
    FullBuildSim<ValidatingAdmissionPolicyList, ValidatingAdmissionPolicyList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingAdmissionPolicyListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyList, ValidatingAdmissionPolicyList.Builder> {
                scenario {
                    id = "minimum"
                    given(ValidatingAdmissionPolicyList.Builder()) {
                        items { policy {  }}
                    }
                    expected = ValidatingAdmissionPolicyList(
                        items = listOf(ValidatingAdmissionPolicy()),
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyList, ValidatingAdmissionPolicyList.Builder> {
                requireNotEmptyScenario("items") {
                    given(ValidatingAdmissionPolicyList.Builder())
                }
            }
    }
}