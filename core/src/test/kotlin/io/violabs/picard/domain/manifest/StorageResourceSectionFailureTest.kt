package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageResourceSectionFailureTest : FailureBuildSim<StorageResourceSection, StorageResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<StorageResourceSection, StorageResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(StorageResourceSection.Builder())
            }
        }
    }
}