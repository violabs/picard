package io.violabs.picard.domain.k8sResources.storage.csi.csiNode


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSINodeDriverTest : FailureBuildSim<CSINodeDriver, CSINodeDriverDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSINodeDriverTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSINodeDriver, CSINodeDriverDslBuilder> {
            requireScenario("name") {
                given(CSINodeDriverDslBuilder())
            }

            requireScenario("nodeID") {
                given(CSINodeDriverDslBuilder()) {
                    name = PLACEHOLDER
                }
            }
        }
    }
}