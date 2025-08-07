package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.possibilities
import io.violabs.picard.verifyHappyPath
import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test

class ContainerResourceRequirementsTest :
    SuccessBuildSim<ContainerResourceRequirements, ContainerResourceRequirementsDslBuilder>() {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                ContainerResourceRequirementsTest::class,
                SUCCESS_POSSIBILITIES
            )
        }
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<ContainerResourceRequirements, ContainerResourceRequirementsDslBuilder> {
    val quantity = Quantity("100m")

    scenario {
        id = "default list provided"
        given(ContainerResourceRequirementsDslBuilder()) {
            limits {}

            requests {}

            limits {
                put("cpu", quantity)
            }

            requests {
                put("cpu", quantity)
            }
        }
        expected = ContainerResourceRequirements(
            limits = mutableMapOf("cpu" to quantity),
            requests = mutableMapOf("cpu" to quantity)
        )
    }
}