package io.violabs.picard.domain.k8sResources.workload.job


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.Operator
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class OnExitCodesRequirementTest : FailureBuildSim<OnExitCodesRequirement, OnExitCodesRequirementDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            OnExitCodesRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<OnExitCodesRequirement, OnExitCodesRequirementDslBuilder> {
            requireScenario("operator") {
                given(OnExitCodesRequirementDslBuilder())
            }

            requireNotEmptyScenario("values") {
                given(OnExitCodesRequirementDslBuilder()) {
                    operator = Operator.In
                }
            }
        }
    }
}