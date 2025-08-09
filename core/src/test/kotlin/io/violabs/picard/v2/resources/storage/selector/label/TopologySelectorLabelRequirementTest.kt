package io.violabs.picard.v2.resources.storage.selector.label

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TopologySelectorLabelRequirementTest :
    FailureBuildSim<TopologySelectorLabelRequirement, TopologySelectorLabelRequirementDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TopologySelectorLabelRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<TopologySelectorLabelRequirement, TopologySelectorLabelRequirementDslBuilder> {
                requireScenario("key") {
                    given(TopologySelectorLabelRequirementDslBuilder())
                }

                requireNotEmptyScenario("values") {
                    given(TopologySelectorLabelRequirementDslBuilder()) {
                        key = PLACEHOLDER
                    }
                }
            }
    }
}