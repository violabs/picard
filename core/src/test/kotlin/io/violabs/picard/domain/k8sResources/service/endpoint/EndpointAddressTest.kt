package io.violabs.picard.domain.k8sResources.service.endpoint


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointAddressTest : FailureBuildSim<EndpointAddress, EndpointAddress.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointAddressTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<EndpointAddress, EndpointAddress.Builder> {
            requireScenario("ip") {
                given(EndpointAddress.Builder())
            }
        }
    }
}