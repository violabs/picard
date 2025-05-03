package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceRequestTest : FailureBuildSim<DeviceRequest, DeviceRequest.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceRequestTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<DeviceRequest, DeviceRequest.Builder> {
            requireScenario("deviceClassName") {
                given(DeviceRequest.Builder())
            }

            requireScenario("name") {
                given(DeviceRequest.Builder()) {
                    deviceClassName = PLACEHOLDER
                }
            }
        }
    }
}