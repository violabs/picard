package io.violabs.picard.domain.k8sResources.cluster


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonEndpointTest : FailureBuildSim<DaemonEndpoint, DaemonEndpoint.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonEndpointTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<DaemonEndpoint, DaemonEndpoint.Builder> {
            requireScenario("port") {
                given(DaemonEndpoint.Builder())
            }
        }
    }
}