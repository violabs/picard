package io.violabs.picard.v2.resources.workload.resource.slice.device

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceTest : FailureBuildSim<Device, DeviceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Device, DeviceDslBuilder> {
            requireScenario("name") {
                given(DeviceDslBuilder())
            }
        }
    }
}