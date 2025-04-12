package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodResourceClaimTest : FailureBuildSim<PodResourceClaim, PodResourceClaim.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodResourceClaimTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<PodResourceClaim, PodResourceClaim.Builder> {
    requireScenario("name") {
        given(PodResourceClaim.Builder())
    }
}