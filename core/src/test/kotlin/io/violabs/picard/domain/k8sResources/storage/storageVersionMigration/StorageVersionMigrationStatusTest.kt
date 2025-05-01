package io.violabs.picard.domain.k8sResources.storage.storageVersionMigration


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageVersionMigrationStatusTest :
    FailureBuildSim<StorageVersionMigration.Status, StorageVersionMigration.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageVersionMigrationStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<StorageVersionMigration.Status, StorageVersionMigration.Status.Builder> {
                requireNotEmptyScenario("conditions") {
                    given(StorageVersionMigration.Status.Builder())
                }
            }
    }
}