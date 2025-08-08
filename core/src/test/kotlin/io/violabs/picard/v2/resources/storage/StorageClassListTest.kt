package io.violabs.picard.v2.resources.storage

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageClassListTest : FullBuildSim<StorageClassList, StorageClassListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StorageClassList, StorageClassListDslBuilder> {
            scenario {
                id = "minimum"
                given(StorageClassListDslBuilder()) {
                    items {
                        storageClass {
                            provisioner = PLACEHOLDER
                        }
                    }
                }
                expected = StorageClassList(
                    items = listOf(
                        StorageClass(
                            provisioner = PLACEHOLDER
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<StorageClassList, StorageClassListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(StorageClassListDslBuilder())
            }
        }
    }
}