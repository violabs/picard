package io.violabs.picard.v2.resources.storage.version.migration


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageVersionMigrationListTest :
    FullBuildSim<StorageVersionMigrationList, StorageVersionMigrationListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageVersionMigrationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<StorageVersionMigrationList, StorageVersionMigrationListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(StorageVersionMigrationListDslBuilder()) {
                        items {
                            storageVersionMigration {  }
                        }
                    }
                    expected = StorageVersionMigrationList(items = listOf(
                        StorageVersionMigration()
                    ))
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<StorageVersionMigrationList, StorageVersionMigrationListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(StorageVersionMigrationListDslBuilder())
                }
            }
    }
}