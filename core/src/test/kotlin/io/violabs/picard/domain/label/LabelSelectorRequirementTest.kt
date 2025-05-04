package io.violabs.picard.domain.label


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LabelSelectorRequirementTest : FailureBuildSim<LabelSelectorRequirement, LabelSelectorRequirement.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LabelSelectorRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LabelSelectorRequirement, LabelSelectorRequirement.Builder> {
            requireScenario("key") {
                given(LabelSelectorRequirement.Builder())
            }

            requireScenario("operator") {
                given(LabelSelectorRequirement.Builder()) {
                    key = PLACEHOLDER
                }
            }
        }
    }
}