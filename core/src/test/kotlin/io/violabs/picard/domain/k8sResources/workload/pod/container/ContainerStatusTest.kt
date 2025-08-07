package io.violabs.picard.domain.k8sResources.workload.pod.container


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerStatusTest : FailureBuildSim<ContainerStatus, ContainerStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ContainerStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ContainerStatus, ContainerStatusDslBuilder> {
            val placeholder = "placeholder"

            requireScenario("imageID") {
                given(ContainerStatusDslBuilder())
            }

            requireScenario("image") {
                given(ContainerStatusDslBuilder()) {
                    imageID = placeholder
                }
            }

            requireScenario("name") {
                given(ContainerStatusDslBuilder()) {
                    imageID = placeholder
                    image = placeholder
                }
            }

            requireScenario("ready") {
                given(ContainerStatusDslBuilder()) {
                    imageID = placeholder
                    image = placeholder
                    name = placeholder
                }
            }
        }
    }
}