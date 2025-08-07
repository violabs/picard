package io.violabs.picard.domain.k8sResources.workload.resourceSlice


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourcePoolTest : FailureBuildSim<ResourcePool, ResourcePoolDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourcePoolTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourcePool, ResourcePoolDslBuilder> {
            requireScenario("generation") {
                given(ResourcePoolDslBuilder())
            }

            requireScenario("name") {
                given(ResourcePoolDslBuilder()) {
                    generation = 1
                }
            }

            requireScenario("resourceSliceCount") {
                given(ResourcePoolDslBuilder()) {
                    generation = 1
                    name = PLACEHOLDER
                }
            }
        }
    }
}