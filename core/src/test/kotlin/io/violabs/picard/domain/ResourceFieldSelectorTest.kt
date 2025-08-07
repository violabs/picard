package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceFieldSelectorTest : FailureBuildSim<ResourceFieldSelector, ResourceFieldSelectorDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceFieldSelectorTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceFieldSelector, ResourceFieldSelectorDslBuilder> {
            requireScenario("resource") {
                given(ResourceFieldSelectorDslBuilder())
            }

            requireScenario("containerName") {
                given(ResourceFieldSelectorDslBuilder()) {
                    resource = PLACEHOLDER
                }
            }
        }
    }
}