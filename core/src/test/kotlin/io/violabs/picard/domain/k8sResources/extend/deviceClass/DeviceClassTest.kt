package io.violabs.picard.domain.k8sResources.extend.deviceClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.DeviceSelector
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceClassTest : FullBuildSim<DeviceClass, DeviceClass.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeviceClass, DeviceClass.Builder> {
            scenario {
                id = "minimum"
                given(DeviceClass.Builder()) {
                    spec {}
                }
                expected = DeviceClass(
                    spec = DeviceClass.Spec()
                )
            }

            scenario {
                id = "full"
                given(DeviceClass.Builder()) {
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
                    spec = DeviceClass.Spec(
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

        private val FAILURE_POSSIBILITIES = possibilities<DeviceClass, DeviceClass.Builder> {
            requireScenario("spec") {
                given(DeviceClass.Builder())
            }
        }
    }
}