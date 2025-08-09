package io.violabs.picard.v2.resources.workload.batch.job.policy.failure


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodFailurePolicyOnExitCodesRequirementTest :
    FailureBuildSim<PodFailurePolicyOnExitCodesRequirement, PodFailurePolicyOnExitCodesRequirementDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodFailurePolicyOnExitCodesRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<PodFailurePolicyOnExitCodesRequirement, PodFailurePolicyOnExitCodesRequirementDslBuilder> {
            requireScenario("operator") {
                given(PodFailurePolicyOnExitCodesRequirementDslBuilder())
            }

            requireNotEmptyScenario("values") {
                given(PodFailurePolicyOnExitCodesRequirementDslBuilder()) {
                    operator = PodFailurePolicyOnExitCodesRequirement.Operator.In
                }
            }
        }
    }
}