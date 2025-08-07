package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodResourceClaimTest : FailureBuildSim<PodResourceClaim, PodResourceClaimDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodResourceClaimTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<PodResourceClaim, PodResourceClaimDslBuilder> {
    requireScenario("name") {
        given(PodResourceClaimDslBuilder())
    }
}