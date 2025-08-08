package io.violabs.picard.v2.resources.workload.resource.slice


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceSliceSpecTest : FailureBuildSim<ResourceSliceSpec, ResourceSliceSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceSliceSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceSliceSpec, ResourceSliceSpecDslBuilder> {
            requireScenario("driver") {
                given(ResourceSliceSpecDslBuilder())
            }

            requireScenario("pool") {
                given(ResourceSliceSpecDslBuilder()) {
                    driver = PLACEHOLDER
                }
            }
        }
    }
}