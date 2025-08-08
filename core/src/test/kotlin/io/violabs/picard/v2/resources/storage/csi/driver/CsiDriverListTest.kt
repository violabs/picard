package io.violabs.picard.v2.resources.storage.csi.driver


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiDriverListTest : FullBuildSim<CsiDriverList, CsiDriverListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiDriverListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CsiDriverList, CsiDriverListDslBuilder> {
            scenario {
                id = "minimum"
                given(CsiDriverListDslBuilder()) {
                    items {
                        csiDriver {  }
                    }
                }
                expected = CsiDriverList(
                    items = listOf(CsiDriver())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CsiDriverList, CsiDriverListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(CsiDriverListDslBuilder())
            }
        }
    }
}