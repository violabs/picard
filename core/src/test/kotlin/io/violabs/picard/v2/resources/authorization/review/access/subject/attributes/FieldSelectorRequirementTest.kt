package io.violabs.picard.v2.resources.authorization.review.access.subject.attributes

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FieldSelectorRequirementTest : FailureBuildSim<FieldSelectorRequirement, FieldSelectorRequirementDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FieldSelectorRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<FieldSelectorRequirement, FieldSelectorRequirementDslBuilder> {
                requireScenario("key") {
                    given(FieldSelectorRequirementDslBuilder())
                }

                requireScenario("requirements") {
                    given(FieldSelectorRequirementDslBuilder()) {
                        key = PLACEHOLDER
                    }
                }
            }
    }
}