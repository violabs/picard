package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterResourceSectionFailureTest : FailureBuildSim<ClusterResourceSection, ClusterResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ClusterResourceSection, ClusterResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(ClusterResourceSection.Builder())
            }
        }
    }
}