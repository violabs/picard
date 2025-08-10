package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class WorkloadResourceSectionFailureTest : FailureBuildSim<WorkloadResourceSection, WorkloadResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            WorkloadResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<WorkloadResourceSection, WorkloadResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(WorkloadResourceSection.Builder())
            }
        }
    }
}