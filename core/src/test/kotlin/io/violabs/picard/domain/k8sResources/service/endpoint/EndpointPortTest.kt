package io.violabs.picard.domain.k8sResources.service.endpoint


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointPortTest : FailureBuildSim<EndpointPort, EndpointPort.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointPortTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<EndpointPort, EndpointPort.Builder> {
            requireScenario("port") {
                given(EndpointPort.Builder())
            }
        }
    }
}