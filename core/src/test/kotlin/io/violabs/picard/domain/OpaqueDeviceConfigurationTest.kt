package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class OpaqueDeviceConfigurationTest : FailureBuildSim<OpaqueDeviceConfiguration, OpaqueDeviceConfiguration.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            OpaqueDeviceConfigurationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<OpaqueDeviceConfiguration, OpaqueDeviceConfiguration.Builder> {
                requireScenario("driver") {
                    given(OpaqueDeviceConfiguration.Builder())
                }

                requireScenario("parameters") {
                    given(OpaqueDeviceConfiguration.Builder()) {
                        driver = PLACEHOLDER
                    }
                }
            }
    }
}