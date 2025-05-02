package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageVersionMigrationTest : SuccessBuildSim<StorageVersionMigration, StorageVersionMigration.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageVersionMigrationTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<StorageVersionMigration, StorageVersionMigration.Builder> {
            scenario {
                id = "minimum"
                given(StorageVersionMigration.Builder())
                expected = StorageVersionMigration()
            }

            scenario {
                id = "full"
                given(StorageVersionMigration.Builder()) {
                    sharedMetadata()
                    spec {
                        resource {
                            group = PLACEHOLDER
                            version = PLACEHOLDER
                            resource = PLACEHOLDER
                        }
                        continueToken = PLACEHOLDER
                    }
                    this.status {
                        conditions {
                            sharedCondition()
                        }
                        resourceVersion = PLACEHOLDER
                    }
                }
                expected = StorageVersionMigration(
                    metadata = METADATA,
                    spec = StorageVersionMigration.Spec(
                        resource = GroupVersionResource(
                            group = PLACEHOLDER,
                            version = PLACEHOLDER,
                            resource = PLACEHOLDER
                        ),
                        continueToken = PLACEHOLDER
                    ),
                    status = StorageVersionMigration.Status(
                        conditions = listOf(CONDITION),
                        resourceVersion = PLACEHOLDER
                    )
                )
            }
        }
    }
}