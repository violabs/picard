package io.violabs.picard.domain.k8sResources.storage.csi.csiNode


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSINodeTest : SuccessBuildSim<CSINode, CSINode.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSINodeTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<CSINode, CSINode.Builder> {
            scenario {
                id = "minimum"
                given(CSINode.Builder())
                expected = CSINode()
            }

            scenario {
                id = "full"
                given(CSINode.Builder()) {
                    sharedMetadata()
                    spec {
                        drivers {
                            addCSINodeDriver {
                                name = PLACEHOLDER
                                nodeID = PLACEHOLDER
                                allocatable(1)
                                topologyKeys(PLACEHOLDER)
                            }
                        }
                    }
                }
                expected = CSINode(
                    metadata = METADATA,
                    spec = CSINode.Spec(
                        drivers = listOf(
                            CSINodeDriver(
                                name = PLACEHOLDER,
                                nodeID = PLACEHOLDER,
                                allocatable = CSINodeDriver.Allocatable(1),
                                topologyKeys = PLACEHOLDER_LIST
                            )
                        )
                    )
                )
            }
        }
    }
}