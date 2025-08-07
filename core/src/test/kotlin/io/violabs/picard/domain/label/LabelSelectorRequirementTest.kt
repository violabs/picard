package io.violabs.picard.domain.label


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LabelSelectorRequirementTest : FailureBuildSim<LabelSelectorRequirement, LabelSelectorRequirementDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LabelSelectorRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LabelSelectorRequirement, LabelSelectorRequirementDslBuilder> {
            requireScenario("key") {
                given(LabelSelectorRequirementDslBuilder())
            }

            requireScenario("operator") {
                given(LabelSelectorRequirementDslBuilder()) {
                    key = PLACEHOLDER
                }
            }
        }
    }
}