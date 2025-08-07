package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIDriverListTest : FullBuildSim<CSIDriverList, CSIDriverListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CSIDriverList, CSIDriverListDslBuilder> {
            scenario {
                id = "minimum"
                given(CSIDriverListDslBuilder()) {
                    items {
                        csiDriverItem {  }
                    }
                }
                expected = CSIDriverList(
                    items = listOf(CSIDriver())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CSIDriverList, CSIDriverListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(CSIDriverListDslBuilder())
            }
        }
    }
}