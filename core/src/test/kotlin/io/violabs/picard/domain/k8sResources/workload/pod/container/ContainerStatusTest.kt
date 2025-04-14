package io.violabs.picard.domain.k8sResources.workload.pod.container


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerStatusTest : FailureBuildSim<ContainerStatus, ContainerStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ContainerStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ContainerStatus, ContainerStatus.Builder> {
            val placeholder = "placeholder"

            requireScenario("imageID") {
                given(ContainerStatus.Builder())
            }

            requireScenario("image") {
                given(ContainerStatus.Builder()) {
                    imageID = placeholder
                }
            }

            requireScenario("name") {
                given(ContainerStatus.Builder()) {
                    imageID = placeholder
                    image = placeholder
                }
            }

            requireScenario("ready") {
                given(ContainerStatus.Builder()) {
                    imageID = placeholder
                    image = placeholder
                    name = placeholder
                }
            }
        }
    }
}