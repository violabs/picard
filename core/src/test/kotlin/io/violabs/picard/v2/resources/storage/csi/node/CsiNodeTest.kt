package io.violabs.picard.v2.resources.storage.csi.node


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiNodeTest : FullBuildSim<CsiNode, CsiNodeV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiNodeTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CsiNode, CsiNodeV2DslBuilder> {
            scenario {
                id = "minimum"
                given(CsiNodeV2DslBuilder()) {
                    spec {
                        drivers { }
                    }
                }
                expected = CsiNode(
                    spec = CsiNodeSpec(
                        drivers = emptyList()
                    )
                )
            }

            scenario {
                id = "full"
                given(CsiNodeV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        drivers {
                            csiNodeDriver {
                                name = PLACEHOLDER
                                nodeId = PLACEHOLDER
                                allocatable(1)
                                topologyKeys(PLACEHOLDER)
                            }
                        }
                    }
                }
                expected = CsiNode(
                    metadata = Common.OBJECT_META,
                    spec = CsiNodeSpec(
                        drivers = listOf(
                            CsiNodeDriver(
                                name = PLACEHOLDER,
                                nodeId = PLACEHOLDER,
                                allocatable = VolumeNodeResources(1),
                                topologyKeys = PLACEHOLDER_LIST
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CsiNode, CsiNodeV2DslBuilder> {
            requireScenario("spec") {
                given(CsiNodeV2DslBuilder())
            }
        }
    }
}