package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AuthorizationResourceSectionFailureTest : FailureBuildSim<AuthorizationResourceSection, AuthorizationResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AuthorizationResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<AuthorizationResourceSection, AuthorizationResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(AuthorizationResourceSection.Builder())
            }
        }
    }
}