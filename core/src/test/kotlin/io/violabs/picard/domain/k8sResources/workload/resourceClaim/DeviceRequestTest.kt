package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceRequestTest : FailureBuildSim<DeviceRequest, DeviceRequestDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceRequestTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<DeviceRequest, DeviceRequestDslBuilder> {
            requireScenario("deviceClassName") {
                given(DeviceRequestDslBuilder())
            }

            requireScenario("name") {
                given(DeviceRequestDslBuilder()) {
                    deviceClassName = PLACEHOLDER
                }
            }
        }
    }
}