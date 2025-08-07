package io.violabs.picard.domain.k8sResources.service.endpoint


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointAddressTest : FailureBuildSim<EndpointAddress, EndpointAddressDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointAddressTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<EndpointAddress, EndpointAddressDslBuilder> {
            requireScenario("ip") {
                given(EndpointAddressDslBuilder())
            }
        }
    }
}