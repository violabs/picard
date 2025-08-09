package io.violabs.picard.v2.resources.cluster.node.daemon

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonEndpointTest : FailureBuildSim<DaemonEndpoint, DaemonEndpointDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonEndpointTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<DaemonEndpoint, DaemonEndpointDslBuilder> {
            requireScenario("port") {
                given(DaemonEndpointDslBuilder())
            }
        }
    }
}