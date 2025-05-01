package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageVersionMigrationListTest :
    FullBuildSim<StorageVersionMigrationList, StorageVersionMigrationList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageVersionMigrationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<StorageVersionMigrationList, StorageVersionMigrationList.Builder> {
                scenario {
                    id = "minimum"
                    given(StorageVersionMigrationList.Builder()) {
                        items {
                            migration {  }
                        }
                    }
                    expected = StorageVersionMigrationList(items = listOf(
                        StorageVersionMigration()
                    ))
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<StorageVersionMigrationList, StorageVersionMigrationList.Builder> {
                requireNotEmptyScenario("items") {
                    given(StorageVersionMigrationList.Builder())
                }
            }
    }
}