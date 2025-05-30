package io.violabs.picard.domain.k8sResources.workload.pod.container


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerResourceClaimTest : FailureBuildSim<ContainerResourceClaim, ContainerResourceClaim.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ContainerResourceClaimTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<ContainerResourceClaim, ContainerResourceClaim.Builder> {
    requireScenario("name") {
        given(ContainerResourceClaim.Builder())
    }
}