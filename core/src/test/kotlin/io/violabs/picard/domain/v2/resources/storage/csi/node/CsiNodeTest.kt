package io.violabs.picard.domain.v2.resources.storage.csi.node


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.storage.csi.node.*
import org.junit.jupiter.api.BeforeAll

class CsiNodeTest : FullBuildSim<CsiNodeV2, CsiNodeV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiNodeTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CsiNodeV2, CsiNodeV2DslBuilder> {
            scenario {
                id = "minimum"
                given(CsiNodeV2DslBuilder()) {
                    spec {
                        drivers { }
                    }
                }
                expected = CsiNodeV2(
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
                expected = CsiNodeV2(
                    metadata = Common.OBJECT_META,
                    spec = CsiNodeSpec(
                        drivers = listOf(
                            CsiNodeDriver(
                                name = PLACEHOLDER,
                                nodeId = PLACEHOLDER,
                                allocatable = VolumeNodeResource(1),
                                topologyKeys = PLACEHOLDER_LIST
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CsiNodeV2, CsiNodeV2DslBuilder> {
            requireScenario("spec") {
                given(CsiNodeV2DslBuilder())
            }
        }
    }
}