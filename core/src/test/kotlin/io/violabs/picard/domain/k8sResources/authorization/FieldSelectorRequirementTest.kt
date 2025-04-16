package io.violabs.picard.domain.k8sResources.authorization


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FieldSelectorRequirementTest : FailureBuildSim<FieldSelectorRequirement, FieldSelectorRequirement.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FieldSelectorRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<FieldSelectorRequirement, FieldSelectorRequirement.Builder> {
            requireScenario("key") {
                given(FieldSelectorRequirement.Builder())
            }

            requireScenario("requirements") {
                given(FieldSelectorRequirement.Builder()) {
                    key = PLACEHOLDER
                }
            }
        }
    }
}