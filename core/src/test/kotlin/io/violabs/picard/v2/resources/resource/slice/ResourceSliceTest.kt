package io.violabs.picard.v2.resources.resource.slice

import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceSliceTest : SuccessBuildSim<ResourceSliceV2, ResourceSliceV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceSliceTest::class,
            SUCCESS_POSSIBILITIES
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
                            }
                        }
                        nodeName = PLACEHOLDER
                        sharedCounters {
                            counterSet {
                                name = PLACEHOLDER
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
                                allNodes = true
                            )
                        ),
                        nodeName = PLACEHOLDER,
                        sharedCounters = listOf(
                            CounterSet(
                                name = PLACEHOLDER
                            )
                        )
                    )
                )
            }
        }
    }
}