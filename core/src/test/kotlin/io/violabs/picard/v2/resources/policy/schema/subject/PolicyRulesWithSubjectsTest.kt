package io.violabs.picard.v2.resources.policy.schema.subject

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PolicyRulesWithSubjectsTest : FailureBuildSim<PolicyRulesWithSubjects, PolicyRulesWithSubjectsDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PolicyRulesWithSubjectsTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PolicyRulesWithSubjects, PolicyRulesWithSubjectsDslBuilder> {
            requireNotEmptyScenario("subjects") {
                given(PolicyRulesWithSubjectsDslBuilder())
            }
        }
    }
}