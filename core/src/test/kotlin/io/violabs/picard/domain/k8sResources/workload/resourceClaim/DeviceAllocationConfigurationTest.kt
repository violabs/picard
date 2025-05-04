package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceAllocationConfigurationTest :
    FailureBuildSim<DeviceAllocationConfiguration, DeviceAllocationConfiguration.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceAllocationConfigurationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<DeviceAllocationConfiguration, DeviceAllocationConfiguration.Builder> {
                requireScenario("source") {
                    given(DeviceAllocationConfiguration.Builder())
                }
            }
    }
}