package io.violabs.picard.k8sResources.workload.pod.container

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.TestScenarioSet
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class ContainerTest : UnitSim() {

    @Test
    fun `build minimum exception`() = test<Unit> {
        given {
            val builder = Container.Builder()

            wheneverThrows<IllegalArgumentException> {
                whenFn = { builder.build() }
                result = {
                    assertOrLog(
                        it.message,
                        "Container name must not be null"
                    )
                }
            }
        }
    }

    @Test
    fun `build minimum happy path`() = test {
        given {
            val builder = Container.Builder().apply {
                name = "test"
            }

            expect { Container(name = "test") }

            whenever { builder.build() }
        }
    }

    @TestTemplate
    fun `build varied happy path - #scenario`(json: Any) = test<Unit> {
        println(json)
    }

    companion object {
        private val SIMULATION_GROUP = SimulationGroup
            .vars("scenario", "json")

        @JvmStatic
        @BeforeAll
        fun setup() {
            val resource = getTestFile("container/container_possibilities.json")
            val om = ObjectMapper()
            val scenarioSet = om.readValue<TestScenarioSet>(resource)
            scenarioSet.scenarios.forEach {
                SIMULATION_GROUP.with(it.id, it.content)
            }

            setup<ContainerTest>(
                SIMULATION_GROUP to { this::`build varied happy path - #scenario` }
            )
        }
    }
}