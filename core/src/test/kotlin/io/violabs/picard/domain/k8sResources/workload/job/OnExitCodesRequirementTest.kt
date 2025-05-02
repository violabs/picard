package io.violabs.picard.domain.k8sResources.workload.job


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.Operator
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class OnExitCodesRequirementTest : FailureBuildSim<OnExitCodesRequirement, OnExitCodesRequirement.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            OnExitCodesRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<OnExitCodesRequirement, OnExitCodesRequirement.Builder> {
            requireScenario("operator") {
                given(OnExitCodesRequirement.Builder())
            }

            requireNotEmptyScenario("values") {
                given(OnExitCodesRequirement.Builder()) {
                    operator = Operator.In
                }
            }
        }
    }
}