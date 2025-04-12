package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerPodResourceClaimTest : FailureBuildSim<ContainerResourceClaim, ContainerResourceClaim.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                ContainerPortTest::class,
                failureScenariosSet = FAILURE_POSSIBILITIES
            )
        }
    }
}


private val FAILURE_POSSIBILITIES = possibilities<ContainerPort, ContainerPort.Builder> {
    scenario {
        id = "missing containerPort"
        given(ContainerPort.Builder())
        exceptionMessage = withTemplate("containerPort")
    }
}