package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIDriverListTest : FullBuildSim<CSIDriverList, CSIDriverList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CSIDriverList, CSIDriverList.Builder> {
            scenario {
                id = "minimum"
                given(CSIDriverList.Builder()) {
                    items {
                        driver {  }
                    }
                }
                expected = CSIDriverList(
                    items = listOf(CSIDriver())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CSIDriverList, CSIDriverList.Builder> {
            requireNotEmptyScenario("items") {
                given(CSIDriverList.Builder())
            }
        }
    }
}