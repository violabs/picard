package io.violabs.picard.v2.resources.workload.resource.device.config

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.extend.deviceclass.config.OpaqueDeviceConfiguration
import io.violabs.picard.v2.resources.extend.deviceclass.config.OpaqueDeviceConfigurationDslBuilder
import org.junit.jupiter.api.BeforeAll

class OpaqueDeviceConfigurationTest : FailureBuildSim<OpaqueDeviceConfiguration, OpaqueDeviceConfigurationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            OpaqueDeviceConfigurationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<OpaqueDeviceConfiguration, OpaqueDeviceConfigurationDslBuilder> {
                requireScenario("driver") {
                    given(OpaqueDeviceConfigurationDslBuilder())
                }

                requireScenario("parameters") {
                    given(OpaqueDeviceConfigurationDslBuilder()) {
                        driver = PLACEHOLDER
                    }
                }
            }
    }
}