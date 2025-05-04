package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TopologySelectorLabelRequirementTest :
    FailureBuildSim<TopologySelectorLabelRequirement, TopologySelectorLabelRequirement.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TopologySelectorLabelRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<TopologySelectorLabelRequirement, TopologySelectorLabelRequirement.Builder> {
                requireScenario("key") {
                    given(TopologySelectorLabelRequirement.Builder())
                }

                requireNotEmptyScenario("values") {
                    given(TopologySelectorLabelRequirement.Builder()) {
                        key = PLACEHOLDER
                    }
                }
            }
    }
}