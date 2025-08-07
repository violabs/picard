package io.violabs.picard.domain.k8sResources.extend.deviceClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.DeviceSelector
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceClassTest : FullBuildSim<DeviceClass, DeviceClassDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeviceClass, DeviceClassDslBuilder> {
            scenario {
                id = "minimum"
                given(DeviceClassDslBuilder()) {
                    spec {}
                }
                expected = DeviceClass(
                    spec = DeviceClassSpec()
                )
            }

            scenario {
                id = "full"
                given(DeviceClassDslBuilder()) {
                    sharedMetadata()
                    spec {
                        config {
                            addDeviceClassConfiguration {
                                opaque {
                                    driver = PLACEHOLDER
                                    parameters = PLACEHOLDER
                                }
                            }
                        }
                        selectors {
                            addDeviceSelector {
                                cel(PLACEHOLDER)
                            }
                        }
                        suitableNodes {
                            terms {
                                term {
                                    sharedNodeSelectorTerm()
                                }
                            }
                        }
                    }
                }
                expected = DeviceClass(
                    metadata = METADATA,
                    spec = DeviceClassSpec(
                        config = listOf(
                            DeviceClassConfiguration(
                                opaque = OPAQUE_DEVICE_CONFIG
                            )
                        ),
                        selectors = listOf(
                            DeviceSelector(
                                cel = DeviceSelector.CEL(expression = PLACEHOLDER)
                            )
                        ),
                        suitableNodes = NODE_SELECTOR
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DeviceClass, DeviceClassDslBuilder> {
            requireScenario("spec") {
                given(DeviceClassDslBuilder())
            }
        }
    }
}