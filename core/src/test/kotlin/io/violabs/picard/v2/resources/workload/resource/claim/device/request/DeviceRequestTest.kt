package io.violabs.picard.v2.resources.workload.resource.claim.device.request

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
            requireScenario("name") {
                given(DeviceRequestDslBuilder())
            }
        }
    }
}