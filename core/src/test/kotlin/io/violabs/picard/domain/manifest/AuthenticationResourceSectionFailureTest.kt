package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AuthenticationResourceSectionFailureTest : FailureBuildSim<AuthenticationResourceSection, AuthenticationResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AuthenticationResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<AuthenticationResourceSection, AuthenticationResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(AuthenticationResourceSection.Builder())
            }
        }
    }
}