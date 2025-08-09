package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ExtendResourceSectionFailureTest : FailureBuildSim<ExtendResourceSection, ExtendResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ExtendResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ExtendResourceSection, ExtendResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(ExtendResourceSection.Builder())
            }
        }
    }
}