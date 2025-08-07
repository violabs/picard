package io.violabs.picard.v2.resources.extend.deviceclass

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.extend.deviceclass.config.DeviceClassConfiguration
import io.violabs.picard.v2.resources.extend.deviceclass.config.OpaqueDeviceConfiguration
import io.violabs.picard.v2.resources.extend.deviceclass.selector.CelDeviceSelector
import io.violabs.picard.v2.resources.extend.deviceclass.selector.DeviceSelector
import org.junit.jupiter.api.BeforeAll

class DeviceClassTest : FullBuildSim<DeviceClass, DeviceClassV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeviceClass, DeviceClassV2DslBuilder> {
            scenario {
                id = "minimum"
                given(DeviceClassV2DslBuilder()) {
                    spec {}
                }
                expected = DeviceClass(
                    spec = DeviceClassSpec()
                )
            }

            scenario {
                id = "full"
                given(DeviceClassV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        config {
                            deviceClassConfiguration {
                                opaque {
                                    driver = PLACEHOLDER
                                    parameters(PLACEHOLDER to PLACEHOLDER)
                                }
                            }
                        }
                        selectors {
                            deviceSelector {
                                cel {
                                    expression = PLACEHOLDER
                                }
                            }
                        }
                    }
                }
                expected = DeviceClass(
                    metadata = Common.OBJECT_META,
                    spec = DeviceClassSpec(
                        config = listOf(
                            DeviceClassConfiguration(
                                opaque = OpaqueDeviceConfiguration(
                                    driver = PLACEHOLDER,
                                    parameters = mapOf(
                                        PLACEHOLDER to PLACEHOLDER
                                    )
                                )
                            )
                        ),
                        selectors = listOf(
                            DeviceSelector(
                                cel = CelDeviceSelector(expression = PLACEHOLDER)
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DeviceClass, DeviceClassV2DslBuilder> {
            requireScenario("spec") {
                given(DeviceClassV2DslBuilder())
            }
        }
    }
}