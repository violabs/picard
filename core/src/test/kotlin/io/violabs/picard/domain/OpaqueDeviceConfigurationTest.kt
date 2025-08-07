package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
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