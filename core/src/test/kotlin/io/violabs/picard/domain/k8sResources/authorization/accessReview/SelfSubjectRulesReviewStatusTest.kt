package io.violabs.picard.domain.k8sResources.authorization.accessReview


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.HTTPVerb
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectRulesReviewStatusTest :
    FailureBuildSim<SelfSubjectRulesReview.Status, SelfSubjectRulesReview.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectRulesReviewStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<SelfSubjectRulesReview.Status, SelfSubjectRulesReview.Status.Builder> {
                requireScenario("incomplete") {
                    given(SelfSubjectRulesReview.Status.Builder())
                }

                requireNotEmptyScenario("nonResourceRules") {
                    given(SelfSubjectRulesReview.Status.Builder()) {
                        incomplete()
                    }
                }

                requireNotEmptyScenario("resourceRules") {
                    given(SelfSubjectRulesReview.Status.Builder()) {
                        incomplete()

                        nonResourceRules {
                            addNonResourceRule {
                                verbs(HTTPVerb.GET)
                            }
                        }
                    }
                }
            }
    }
}