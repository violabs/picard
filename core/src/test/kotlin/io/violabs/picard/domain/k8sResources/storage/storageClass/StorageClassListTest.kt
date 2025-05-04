package io.violabs.picard.domain.k8sResources.storage.storageClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageClassListTest : FullBuildSim<StorageClassList, StorageClassList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StorageClassList, StorageClassList.Builder> {
            scenario {
                id = "minimum"
                given(StorageClassList.Builder()) {
                    items {
                        storageClassItem {
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

        private val FAILURE_POSSIBILITIES = possibilities<StorageClassList, StorageClassList.Builder> {
            requireNotEmptyScenario("items") {
                given(StorageClassList.Builder())
            }
        }
    }
}