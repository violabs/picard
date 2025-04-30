package io.violabs.picard.domain.k8sResources.storage.csi.csiNode


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSINodeDriverTest : FailureBuildSim<CSINodeDriver, CSINodeDriver.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSINodeDriverTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSINodeDriver, CSINodeDriver.Builder> {
            requireScenario("name") {
                given(CSINodeDriver.Builder())
            }

            requireScenario("nodeID") {
                given(CSINodeDriver.Builder()) {
                    name = PLACEHOLDER
                }
            }
        }
    }
}