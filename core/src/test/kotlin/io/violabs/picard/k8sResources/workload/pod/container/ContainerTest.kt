package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.possibilities
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
    fun `build varied happy path - #scenario`(builder: Container.Builder, container: Container) = test {
        given {
            expect { container }

            whenever { builder.build() }
        }
    }

    companion object {
        private val SIMULATION_GROUP = SimulationGroup.vars("scenario", "builder", "container")

        @JvmStatic
        @BeforeAll
        fun setup() {
            SUCCESS_POSSIBILITIES.scenarios.forEach {
                SIMULATION_GROUP.with(it.id, it.given, it.expected)
            }

            setup<ContainerTest>(
                SIMULATION_GROUP to { this::`build varied happy path - #scenario` }
            )
        }
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<Container, Container.Builder> {
    scenario {
        id = "single level"
        description = "single level of optionals. if a child optional has required, it is skipped."
        given(Container.Builder()) {
            name = "test"
            image = "test/image:latest"
            imagePullPolicy = ImagePullPolicy.Always
            command = listOf("echo", "hello")
            args = listOf("hello", "universe")
            workingDir = "/home/test"
        }
        expected = Container(
            name = "test",
            image = "test/image:latest",
            imagePullPolicy = ImagePullPolicy.Always,
            command = listOf("echo", "hello"),
            args = listOf("hello", "universe"),
            workingDir = "/home/test"
        )
    }

    scenario {
        id = "ports minimum happy path"
    }

    scenario {
        id = "ports full happy path"
    }
}

private val EXCEPTION_POSSIBILITIES = possibilities<Container, Container.Builder> {
    scenario {
        id = "ports exception"
    }
}