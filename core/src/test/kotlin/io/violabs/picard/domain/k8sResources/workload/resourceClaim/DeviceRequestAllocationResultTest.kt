package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceRequestAllocationResultTest :
    FailureBuildSim<DeviceRequestAllocationResult, DeviceRequestAllocationResult.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceRequestAllocationResultTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<DeviceRequestAllocationResult, DeviceRequestAllocationResult.Builder> {
                requireScenario("device") {
                    given(DeviceRequestAllocationResult.Builder())
                }

                requireScenario("driver") {
                    given(DeviceRequestAllocationResult.Builder()) {
                        device = PLACEHOLDER
                    }
                }

                requireScenario("pool") {
                    given(DeviceRequestAllocationResult.Builder()) {
                        device = PLACEHOLDER
                        driver = PLACEHOLDER
                    }
                }

                requireScenario("request") {
                    given(DeviceRequestAllocationResult.Builder()) {
                        device = PLACEHOLDER
                        driver = PLACEHOLDER
                        pool = PLACEHOLDER
                    }
                }
            }
    }
}