package io.violabs.picard.domain.k8sResources.storage.csi.csiNode


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSINodeListTest : FullBuildSim<CSINodeList, CSINodeList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSINodeListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CSINodeList, CSINodeList.Builder> {
            scenario {
                id = "minimum"
                given(CSINodeList.Builder()) {
                    items {
                        node {}
                    }
                }
                expected = CSINodeList(
                    items = listOf(CSINode())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CSINodeList, CSINodeList.Builder> {
            requireNotEmptyScenario("items") {
                given(CSINodeList.Builder())
            }
        }
    }
}