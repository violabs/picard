package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceResourceSectionFailureTest : FailureBuildSim<ServiceResourceSection, ServiceResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ServiceResourceSection, ServiceResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(ServiceResourceSection.Builder())
            }
        }
    }
}