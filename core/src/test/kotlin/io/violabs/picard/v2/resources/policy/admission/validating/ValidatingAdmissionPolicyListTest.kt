package io.violabs.picard.v2.resources.policy.admission.validating

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingAdmissionPolicyListTest :
    FullBuildSim<ValidatingAdmissionPolicyList, ValidatingAdmissionPolicyListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingAdmissionPolicyListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyList, ValidatingAdmissionPolicyListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(ValidatingAdmissionPolicyListDslBuilder()) {
                        items { validatingAdmissionPolicy { } }
                    }
                    expected = ValidatingAdmissionPolicyList(
                        items = listOf(ValidatingAdmissionPolicy()),
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ValidatingAdmissionPolicyList, ValidatingAdmissionPolicyListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(ValidatingAdmissionPolicyListDslBuilder())
                }
            }
    }
}