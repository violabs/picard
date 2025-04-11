package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerPortTest : FullBuildSim<ContainerPort, ContainerPort.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                ContainerPortTest::class,
                SUCCESS_POSSIBILITIES,
                FAILURE_POSSIBILITIES
            )
        }
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<ContainerPort, ContainerPort.Builder> {
    scenario {
        id = "minimum"
        given(ContainerPort.Builder()) {
            containerPort = 8080
        }
        expected = ContainerPort(containerPort = 8080)
    }
}

private val FAILURE_POSSIBILITIES = possibilities<ContainerPort, ContainerPort.Builder> {
    scenario {
        id = "missing containerPort"
        given(ContainerPort.Builder())
        exceptionMessage = withTemplate("containerPort")
    }
}