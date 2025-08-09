package io.violabs.picard.v2.resources.storage.csi.storage.capacity

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiStorageCapacityListTest : FullBuildSim<CsiStorageCapacityList, CsiStorageCapacityListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiStorageCapacityListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CsiStorageCapacityList, CsiStorageCapacityListDslBuilder> {
            scenario {
                id = "minimum"
                given(CsiStorageCapacityListDslBuilder()) {
                    items {
                        csiStorageCapacity {
                            storageClassName = PLACEHOLDER
                        }
                    }
                }
                expected = CsiStorageCapacityList(
                    items = listOf(CsiStorageCapacity(storageClassName = PLACEHOLDER))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CsiStorageCapacityList, CsiStorageCapacityListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(CsiStorageCapacityListDslBuilder())
            }
        }
    }
}