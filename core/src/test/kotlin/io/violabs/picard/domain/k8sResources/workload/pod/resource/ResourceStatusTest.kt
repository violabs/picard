package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceStatusTest : FailureBuildSim<ResourceStatus, ResourceStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceStatus, ResourceStatusDslBuilder> {
            val sharedName = "test"

            requireScenario("name") {
                given(ResourceStatusDslBuilder())
            }

            requireScenario("image") {
                given(ResourceStatusDslBuilder()) {
                    name = sharedName
                }
            }

            requireScenario("imageID") {
                given(ResourceStatusDslBuilder()) {
                    name = sharedName
                    image = "test_image"
                }
            }
        }
    }
}