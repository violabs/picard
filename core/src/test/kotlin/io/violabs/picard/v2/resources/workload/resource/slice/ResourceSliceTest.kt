package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.Workload
import io.violabs.picard.Workload.Resource.sharedTerms
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.resource.NodeSelector
import io.violabs.picard.v2.resources.workload.resource.device.taint.DeviceTaint
import io.violabs.picard.v2.resources.workload.resource.slice.device.Counter
import io.violabs.picard.v2.resources.workload.resource.slice.device.CounterDslBuilder
import io.violabs.picard.v2.resources.workload.resource.slice.device.CounterSet
import io.violabs.picard.v2.resources.workload.resource.slice.device.Device
import io.violabs.picard.v2.resources.workload.resource.slice.device.DeviceAttribute
import io.violabs.picard.v2.resources.workload.resource.slice.device.DeviceCapacity
import io.violabs.picard.v2.resources.workload.resource.slice.device.DeviceCounterConsumption
import org.junit.jupiter.api.BeforeAll

class ResourceSliceTest : SuccessBuildSim<ResourceSliceV2, ResourceSliceV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceSliceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private fun CounterDslBuilder.MapGroup<String>.sharedCounters() {
            counter(PLACEHOLDER) {
                value(PLACEHOLDER)
            }
        }

        private val COUNTERS = mapOf(
            PLACEHOLDER to Counter(
                value = QUANTITY
            )
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceSliceV2, ResourceSliceV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceSliceV2DslBuilder()) {
                    spec {
                        driver = PLACEHOLDER
                        pool {
                            generation = 1
                            name = PLACEHOLDER
                            resourceSliceCount = 1
                        }
                    }
                }
                expected = ResourceSliceV2(
                    spec = ResourceSliceSpec(
                        driver = PLACEHOLDER,
                        pool = ResourcePool(
                            generation = 1,
                            name = PLACEHOLDER,
                            resourceSliceCount = 1
                        )
                    )
                )
            }

            scenario {
                id = "full"
                given(ResourceSliceV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        driver = PLACEHOLDER
                        pool {
                            generation = 1
                            name = PLACEHOLDER
                            resourceSliceCount = 1
                        }
                        allNodes()
                        devices {
                            device {
                                name = PLACEHOLDER
                                allNodes()
                                attributes {
                                    deviceAttribute(PLACEHOLDER) {
                                        boolValue()
                                        intValue = 1
                                        stringValue = PLACEHOLDER
                                        versionValue = PLACEHOLDER
                                    }
                                }

                                capacity {
                                    deviceCapacity(PLACEHOLDER) {
                                        value(PLACEHOLDER)
                                    }
                                }

                                consumesCounters {
                                    deviceCounterConsumption {
                                        counterSet = PLACEHOLDER
                                        counters {
                                            sharedCounters()
                                        }
                                    }
                                }

                                nodeName = PLACEHOLDER

                                nodeSelector {
                                    sharedTerms()
                                }

                                taints {
                                    deviceTaint {
                                        key = PLACEHOLDER
                                        value = PLACEHOLDER
                                        effect = DeviceTaint.Effect.NoSchedule
                                        timeAdded = NOW
                                    }
                                }
                            }
                        }

                        nodeName = PLACEHOLDER

                        nodeSelector {
                            sharedTerms()
                        }

                        perDeviceNodeSelection()

                        sharedCounters {
                            counterSet {
                                name = PLACEHOLDER
                                counters {
                                    sharedCounters()
                                }
                            }
                        }
                    }
                }
                expected = ResourceSliceV2(
                    metadata = OBJECT_META,
                    spec = ResourceSliceSpec(
                        driver = PLACEHOLDER,
                        pool = ResourcePool(
                            generation = 1,
                            name = PLACEHOLDER,
                            resourceSliceCount = 1
                        ),
                        allNodes = true,
                        devices = listOf(
                            Device(
                                name = PLACEHOLDER,
                                allNodes = true,
                                attributes = mapOf(
                                    PLACEHOLDER to DeviceAttribute(
                                        boolValue = true,
                                        intValue = 1,
                                        stringValue = PLACEHOLDER,
                                        versionValue = PLACEHOLDER
                                    )
                                ),
                                capacity = mapOf(
                                    PLACEHOLDER to DeviceCapacity(
                                        value = QUANTITY
                                    )
                                ),
                                consumesCounters = listOf(
                                    DeviceCounterConsumption(
                                        counterSet = PLACEHOLDER,
                                        counters = COUNTERS
                                    )
                                ),
                                nodeName = PLACEHOLDER,
                                nodeSelector = NodeSelector(
                                    nodeSelectorTerms = Workload.Resource.NODE_SELECTOR_TERMS
                                ),
                                taints = listOf(
                                    DeviceTaint(
                                        key = PLACEHOLDER,
                                        value = PLACEHOLDER,
                                        effect = DeviceTaint.Effect.NoSchedule,
                                        timeAdded = NOW
                                    )
                                )
                            )
                        ),
                        nodeName = PLACEHOLDER,
                        nodeSelector = NodeSelector(
                            nodeSelectorTerms = Workload.Resource.NODE_SELECTOR_TERMS
                        ),
                        perDeviceNodeSelection = true,
                        sharedCounters = listOf(
                            CounterSet(
                                name = PLACEHOLDER,
                                counters = COUNTERS
                            )
                        )
                    )
                )
            }
        }
    }
}