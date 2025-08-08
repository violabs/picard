package io.violabs.picard.v2.resources.workload.resource.claim.device.config

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceAllocationConfigurationTest :
    FailureBuildSim<DeviceAllocationConfiguration, DeviceAllocationConfigurationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceAllocationConfigurationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<DeviceAllocationConfiguration, DeviceAllocationConfigurationDslBuilder> {
                requireScenario("source") {
                    given(DeviceAllocationConfigurationDslBuilder())
                }
            }
    }
}