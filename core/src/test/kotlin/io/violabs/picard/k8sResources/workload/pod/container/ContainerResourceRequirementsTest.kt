package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerResourceRequirements
import io.violabs.picard.verifyHappyPath
import kotlin.test.Test

class ContainerResourceRequirementsTest : UnitSim() {
    private val quantity = Quantity("100m")

    @Test
    fun `build existing list test happy path`() = verifyHappyPath(
        ContainerResourceRequirements.Builder(),
        ContainerResourceRequirements(
            limits = mutableMapOf("cpu" to quantity),
            requests = mutableMapOf("cpu" to quantity)
        )
    ) {
        limits {

        }

        requests {

        }

        limits {
            put("cpu", quantity)
        }

        requests {
            put("cpu", quantity)
        }
    }
}