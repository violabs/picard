package io.violabs.picard.domain.k8sResources.workload.job


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
            requireNotEmptyScenario("rules") {
                given(PodFailurePolicyDslBuilder())
            }
        }
    }
}