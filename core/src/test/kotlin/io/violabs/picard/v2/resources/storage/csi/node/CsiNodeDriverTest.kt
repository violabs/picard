package io.violabs.picard.v2.resources.storage.csi.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiNodeDriverTest : FailureBuildSim<CsiNodeDriver, CsiNodeDriverDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiNodeDriverTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CsiNodeDriver, CsiNodeDriverDslBuilder> {
            requireScenario("name") {
                given(CsiNodeDriverDslBuilder())
            }

            requireScenario("nodeID") {
                given(CsiNodeDriverDslBuilder()) {
                    name = PLACEHOLDER
                }
            }
        }
    }
}