package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceStatusTest : FailureBuildSim<ResourceStatus, ResourceStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceStatus, ResourceStatus.Builder> {
            val sharedName = "test"

            requireScenario("name") {
                given(ResourceStatus.Builder())
            }

            requireScenario("image") {
                given(ResourceStatus.Builder()) {
                    name = sharedName
                }
            }

            requireScenario("imageID") {
                given(ResourceStatus.Builder()) {
                    name = sharedName
                    image = "test_image"
                }
            }
        }
    }
}