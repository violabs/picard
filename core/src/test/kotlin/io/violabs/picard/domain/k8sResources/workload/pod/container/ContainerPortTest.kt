package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerPortTest : FullBuildSim<ContainerPort, ContainerPortDslBuilder>() {
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

private val SUCCESS_POSSIBILITIES = possibilities<ContainerPort, ContainerPortDslBuilder> {
    scenario {
        id = "minimum"
        given(ContainerPortDslBuilder()) {
            containerPort = 8080
        }
        expected = ContainerPort(containerPort = 8080)
    }
}

private val FAILURE_POSSIBILITIES = possibilities<ContainerPort, ContainerPortDslBuilder> {
    scenario {
        id = "missing containerPort"
        given(ContainerPortDslBuilder())
        exceptionMessage = withTemplate("containerPort")
    }
}