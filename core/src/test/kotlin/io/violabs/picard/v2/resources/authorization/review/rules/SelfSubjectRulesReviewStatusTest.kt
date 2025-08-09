package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectRulesReviewStatusTest :
    FailureBuildSim<SelfSubjectRulesReviewStatus, SelfSubjectRulesReviewStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectRulesReviewStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<SelfSubjectRulesReviewStatus, SelfSubjectRulesReviewStatusDslBuilder> {
                requireScenario("incomplete") {
                    given(SelfSubjectRulesReviewStatusDslBuilder())
                }

//                requireNotEmptyScenario("nonResourceRules") {
                requireScenario("nonResourceRules") {
                    given(SelfSubjectRulesReviewStatusDslBuilder()) {
                        incomplete()
                    }
                }

//                requireNotEmptyScenario("resourceRules") {
                requireScenario("resourceRules") {
                    given(SelfSubjectRulesReviewStatusDslBuilder()) {
                        incomplete()

                        nonResourceRules {
                            nonResourceRule {
                                verbs(PLACEHOLDER)
                            }
                        }
                    }
                }
            }
    }
}