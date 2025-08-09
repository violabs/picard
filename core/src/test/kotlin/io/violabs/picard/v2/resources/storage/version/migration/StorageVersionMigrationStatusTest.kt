package io.violabs.picard.v2.resources.storage.version.migration


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageVersionMigrationStatusTest :
    FailureBuildSim<StorageVersionMigrationStatus, StorageVersionMigrationStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageVersionMigrationStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<StorageVersionMigrationStatus, StorageVersionMigrationStatusDslBuilder> {
//                requireNotEmptyScenario("conditions") {
                requireScenario("conditions") {
                    given(StorageVersionMigrationStatusDslBuilder())
                }
            }
    }
}