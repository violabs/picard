package io.violabs.picard.domain.k8sResources.workload


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceTest : FailureBuildSim<Device, Device.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Device, Device.Builder> {
            requireScenario("name") {
                given(Device.Builder())
            }
        }
    }
}