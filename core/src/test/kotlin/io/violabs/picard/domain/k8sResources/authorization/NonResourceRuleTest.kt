package io.violabs.picard.domain.k8sResources.authorization


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NonResourceRuleTest : FailureBuildSim<NonResourceRule, NonResourceRule.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NonResourceRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NonResourceRule, NonResourceRule.Builder> {
            requireNotEmptyScenario("verbs") {
                given(NonResourceRule.Builder())
            }
        }
    }
}