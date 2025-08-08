package io.violabs.picard.v2.resources.workload.batch.job.policy.failure


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodFailurePolicyOnPodConditionsPatternTest :
    FailureBuildSim<PodFailurePolicyOnPodConditionsPattern, PodFailurePolicyOnPodConditionsPatternDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodFailurePolicyOnPodConditionsPatternTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<PodFailurePolicyOnPodConditionsPattern, PodFailurePolicyOnPodConditionsPatternDslBuilder> {
            requireScenario("status") {
                given(PodFailurePolicyOnPodConditionsPatternDslBuilder())
            }

            requireScenario("type") {
                given(PodFailurePolicyOnPodConditionsPatternDslBuilder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}