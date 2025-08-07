package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodResourceClaimStatusTest : FailureBuildSim<PodResourceClaimStatus, PodResourceClaimStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodResourceClaimStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodResourceClaimStatus, PodResourceClaimStatusDslBuilder> {
            requireScenario("name") {
                given(PodResourceClaimStatusDslBuilder())
            }
        }
    }
}