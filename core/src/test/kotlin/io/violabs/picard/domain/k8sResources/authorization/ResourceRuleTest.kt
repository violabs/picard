package io.violabs.picard.domain.k8sResources.authorization


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceRuleTest : FailureBuildSim<ResourceRule, ResourceRule.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceRule, ResourceRule.Builder> {
            requireNotEmptyScenario("verbs") {
                given(ResourceRule.Builder())
            }
        }
    }
}