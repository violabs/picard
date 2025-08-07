package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PolicyRuleSubjectTest : FailureBuildSim<PolicyRuleSubject, PolicyRuleSubjectDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PolicyRuleSubjectTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PolicyRuleSubject, PolicyRuleSubjectDslBuilder> {
            requireScenario("kind") {
                given(PolicyRuleSubjectDslBuilder())
            }
        }
    }
}