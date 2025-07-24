package io.violabs.picard.v2.resources.storage.version.migration


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageVersionMigrationTest : SuccessBuildSim<StorageVersionMigrationV2, StorageVersionMigrationV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageVersionMigrationTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<StorageVersionMigrationV2, StorageVersionMigrationV2DslBuilder> {
                scenario {
                    id = "minimum"
                    given(StorageVersionMigrationV2DslBuilder())
                    expected = StorageVersionMigrationV2()
                }

                scenario {
                    id = "full"
                    given(StorageVersionMigrationV2DslBuilder()) {
                        metadata {
                            sharedObjectMeta()
                        }

                        spec {
                            resource {
                                group = PLACEHOLDER
                                version = PLACEHOLDER
                                resource = PLACEHOLDER
                            }
                            continueToken = PLACEHOLDER
                        }

                        status {
                            conditions {
                                migrationCondition {
                                    type = PLACEHOLDER
                                    status = PLACEHOLDER
                                    lastUpdateTime = NOW
                                    reason = PLACEHOLDER
                                    message = PLACEHOLDER
                                }
                            }
                            resourceVersion = PLACEHOLDER
                        }
                    }
                    expected = StorageVersionMigrationV2(
                        metadata = Common.OBJECT_META,
                        spec = StorageVersionMigrationSpec(
                            resource = GroupVersionResource(
                                group = PLACEHOLDER,
                                version = PLACEHOLDER,
                                resource = PLACEHOLDER
                            ),
                            continueToken = PLACEHOLDER
                        ),
                        status = StorageVersionMigrationStatus(
                            conditions = listOf(
                                MigrationCondition(
                                    type = PLACEHOLDER,
                                    status = PLACEHOLDER,
                                    lastUpdateTime = NOW,
                                    reason = PLACEHOLDER,
                                    message = PLACEHOLDER
                                )
                            ),
                            resourceVersion = PLACEHOLDER
                        )
                    )
                }
            }
    }
}