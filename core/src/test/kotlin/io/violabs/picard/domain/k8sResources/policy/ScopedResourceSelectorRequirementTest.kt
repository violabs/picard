package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.Operator
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ScopedResourceSelectorRequirementTest :
    FailureBuildSim<ScopedResourceSelectorRequirement, ScopedResourceSelectorRequirementDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ScopedResourceSelectorRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ScopedResourceSelectorRequirement, ScopedResourceSelectorRequirementDslBuilder> {
                requireScenario("operator") {
                    given(ScopedResourceSelectorRequirementDslBuilder())
                }

                requireScenario("scopeName") {
                    given(ScopedResourceSelectorRequirementDslBuilder()) {
                        operator = Operator.In
                    }
                }
            }
    }
}