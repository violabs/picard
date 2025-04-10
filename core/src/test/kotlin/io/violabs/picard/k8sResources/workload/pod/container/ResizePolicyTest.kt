package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.ExceptionMessage
import io.violabs.picard.domain.k8sResources.workload.pod.container.ResizePolicy
import io.violabs.picard.possibilities
import io.violabs.picard.verifyRequiredField
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestTemplate

class ResizePolicyTest : UnitSim() {

    @TestTemplate
    fun `build minimum exception - #scenario`(
        builder: ResizePolicy.Builder,
        message: ExceptionMessage
    ) = verifyRequiredField(builder, message)

    companion object {
        private val EXCEPTION_SIMULATION_GROUP = SimulationGroup.vars("scenario", "builder", "message")

        @JvmStatic
        @BeforeAll
        fun setup() {
            EXCEPTION_POSSIBILITIES.scenarios.forEach {
                println(it)
                EXCEPTION_SIMULATION_GROUP.with(it.id, it.given, it.exceptionMessage)
            }

            setup<ResizePolicyTest>(
                EXCEPTION_SIMULATION_GROUP to { this::`build minimum exception - #scenario` }
            )
        }
    }
}

private val EXCEPTION_POSSIBILITIES = possibilities<ResizePolicy, ResizePolicy.Builder> {
    scenario {
        id = "missing resourceName"
        given(ResizePolicy.Builder())
        exceptionMessage = withTemplate("resourceName")
    }

    scenario {
        id = "missing restartPolicy"
        given(ResizePolicy.Builder()) {
            resourceName = "cool-resource"
        }
        exceptionMessage = withTemplate("restartPolicy")
    }
}