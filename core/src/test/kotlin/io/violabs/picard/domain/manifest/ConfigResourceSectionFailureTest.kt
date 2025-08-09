package io.violabs.picard.domain.manifest

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigResourceSectionFailureTest : FailureBuildSim<ConfigResourceSection, ConfigResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigResourceSectionFailureTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ConfigResourceSection, ConfigResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(ConfigResourceSection.Builder())
            }
        }
    }
}