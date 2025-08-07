package io.violabs.picard.domain.k8sResources.workload.resourceSlice


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.workload.Device
import io.violabs.picard.domain.k8sResources.workload.DeviceAttribute
import io.violabs.picard.domain.k8sResources.workload.DeviceBasic
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceSliceTest : FullBuildSim<ResourceSlice, ResourceSliceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceSliceTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val DEVICE = Device(
            name = PLACEHOLDER,
            basic = DeviceBasic(
                attributes = mapOf(
                    PLACEHOLDER to DeviceAttribute(
                        bool = true,
                        int = 1,
                        string = PLACEHOLDER,
                        version = PLACEHOLDER
                    )
                ),
                capacity = QUANTITY_MAP
            )
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceSlice, ResourceSliceDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceSliceDslBuilder()) {
                    spec {
                        driver = PLACEHOLDER
                        pool {
                            generation = 1
                            name = PLACEHOLDER
                            resourceSliceCount = 1
                        }
                    }
                }
                expected = ResourceSlice(
                    spec = ResourceSliceSpec(
                        driver = PLACEHOLDER,
                        pool = RESOURCE_POOL
                    )
                )
            }

            scenario {
                id = "full"
                given(ResourceSliceDslBuilder()) {
                    sharedMetadata()
                    spec {
                        driver = PLACEHOLDER
                        pool {
                            generation = 1
                            name = PLACEHOLDER
                            resourceSliceCount = 1
                        }
                        allNodes()
                        devices {
                            addDevice {
                                name = PLACEHOLDER
                                basic {
                                    attributes {
                                        add(PLACEHOLDER) {
                                            bool()
                                            int = 1
                                            string = PLACEHOLDER
                                            version = PLACEHOLDER
                                        }
                                    }
                                    capacity(QUANTITY_PAIR)
                                }
                            }
                        }
                        nodeName = PLACEHOLDER
                        nodeSelector {
                            terms {
                                term {
                                    sharedNodeSelectorTerm()
                                }
                            }
                        }
                    }
                }
                expected = ResourceSlice(
                    metadata = METADATA,
                    spec = ResourceSliceSpec(
                        driver = PLACEHOLDER,
                        pool = RESOURCE_POOL,
                        allNodes = true,
                        devices = listOf(DEVICE),
                        nodeName = PLACEHOLDER,
                        nodeSelector = NODE_SELECTOR
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceSlice, ResourceSliceDslBuilder> {
            requireScenario("spec") {
                given(ResourceSliceDslBuilder())
            }

        }
    }
}