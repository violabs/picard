package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PolicyResourceSectionFailureTest : FailureBuildSim<PolicyResourceSection, PolicyResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PolicyResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PolicyResourceSection, PolicyResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(PolicyResourceSection.Builder())
            }
        }
    }
}