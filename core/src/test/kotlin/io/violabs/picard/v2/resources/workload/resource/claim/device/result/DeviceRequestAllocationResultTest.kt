package io.violabs.picard.v2.resources.workload.resource.claim.device.result

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceRequestAllocationResultTest :
    FailureBuildSim<DeviceRequestAllocationResult, DeviceRequestAllocationResultDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceRequestAllocationResultTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<DeviceRequestAllocationResult, DeviceRequestAllocationResultDslBuilder> {
                requireScenario("device") {
                    given(DeviceRequestAllocationResultDslBuilder())
                }

                requireScenario("driver") {
                    given(DeviceRequestAllocationResultDslBuilder()) {
                        device = PLACEHOLDER
                    }
                }

                requireScenario("pool") {
                    given(DeviceRequestAllocationResultDslBuilder()) {
                        device = PLACEHOLDER
                        driver = PLACEHOLDER
                    }
                }

                requireScenario("request") {
                    given(DeviceRequestAllocationResultDslBuilder()) {
                        device = PLACEHOLDER
                        driver = PLACEHOLDER
                        pool = PLACEHOLDER
                    }
                }
            }
    }
}