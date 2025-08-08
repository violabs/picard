package io.violabs.picard.v2.resources.storage.csi.node


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiNodeListTest : FullBuildSim<CsiNodeList, CsiNodeListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiNodeListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CsiNodeList, CsiNodeListDslBuilder> {
            scenario {
                id = "minimum"
                given(CsiNodeListDslBuilder()) {
                    items {
                        csiNode {
                            spec {
                                drivers {
                                    csiNodeDriver {
                                        name = PLACEHOLDER
                                        nodeId = PLACEHOLDER
                                    }
                                }
                            }
                        }
                    }
                }
                expected = CsiNodeList(
                    items = listOf(
                        CsiNode(
                            spec = CsiNodeSpec(
                                drivers = listOf(
                                    CsiNodeDriver(
                                        name = PLACEHOLDER,
                                        nodeId = PLACEHOLDER
                                    )
                                )
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CsiNodeList, CsiNodeListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(CsiNodeListDslBuilder())
            }
        }
    }
}