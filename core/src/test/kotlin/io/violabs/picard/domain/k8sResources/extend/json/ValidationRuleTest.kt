package io.violabs.picard.domain.k8sResources.extend.json


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidationRuleTest : FailureBuildSim<ValidationRule, ValidationRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidationRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ValidationRule, ValidationRuleDslBuilder> {
            requireScenario("rule") {
                given(ValidationRuleDslBuilder())
            }
        }
    }
}