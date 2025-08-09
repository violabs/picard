package io.violabs.picard.v2.resources.workload.batch.job.policy.failure


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodFailurePolicyTest : FailureBuildSim<PodFailurePolicy, PodFailurePolicyDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodFailurePolicyTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodFailurePolicy, PodFailurePolicyDslBuilder> {
//            requireNotEmptyScenario("rules") {
            requireScenario("rules") {
                given(PodFailurePolicyDslBuilder())
            }
        }
    }
}