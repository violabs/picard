package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PolicyRulesWithSubjectsTest : FailureBuildSim<PolicyRulesWithSubjects, PolicyRulesWithSubjects.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PolicyRulesWithSubjectsTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PolicyRulesWithSubjects, PolicyRulesWithSubjects.Builder> {
            requireNotEmptyScenario("subjects") {
                given(PolicyRulesWithSubjects.Builder())
            }
        }
    }
}