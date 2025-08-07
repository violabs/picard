package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageVersionMigrationTest : SuccessBuildSim<StorageVersionMigration, StorageVersionMigrationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageVersionMigrationTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<StorageVersionMigration, StorageVersionMigrationDslBuilder> {
            scenario {
                id = "minimum"
                given(StorageVersionMigrationDslBuilder())
                expected = StorageVersionMigration()
            }

            scenario {
                id = "full"
                given(StorageVersionMigrationDslBuilder()) {
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
                    spec = StorageVersionMigrationSpec(
                        resource = GroupVersionResource(
                            group = PLACEHOLDER,
                            version = PLACEHOLDER,
                            resource = PLACEHOLDER
                        ),
                        continueToken = PLACEHOLDER
                    ),
                    status = StorageVersionMigrationStatus(
                        conditions = listOf(CONDITION),
                        resourceVersion = PLACEHOLDER
                    )
                )
            }
        }
    }
}