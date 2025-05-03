package io.violabs.picard.domain.k8sResources.workload.resourceSlice


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourcePoolTest : FailureBuildSim<ResourcePool, ResourcePool.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourcePoolTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourcePool, ResourcePool.Builder> {
            requireScenario("generation") {
                given(ResourcePool.Builder())
            }

            requireScenario("name") {
                given(ResourcePool.Builder()) {
                    generation = 1
                }
            }

            requireScenario("resourceSliceCount") {
                given(ResourcePool.Builder()) {
                    generation = 1
                    name = PLACEHOLDER
                }
            }
        }
    }
}