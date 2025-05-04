package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceFieldSelectorTest : FailureBuildSim<ResourceFieldSelector, ResourceFieldSelector.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceFieldSelectorTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceFieldSelector, ResourceFieldSelector.Builder> {
            requireScenario("resource") {
                given(ResourceFieldSelector.Builder())
            }

            requireScenario("containerName") {
                given(ResourceFieldSelector.Builder()) {
                    resource = PLACEHOLDER
                }
            }
        }
    }
}