package io.violabs.picard.v2.resources.authorization.review.rules


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectRulesReviewSpecTest :
    FailureBuildSim<SelfSubjectRulesReviewSpec, SelfSubjectRulesReviewSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectRulesReviewSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<SelfSubjectRulesReviewSpec, SelfSubjectRulesReviewSpecDslBuilder> {
                requireScenario("namespace") {
                    given(SelfSubjectRulesReviewSpecDslBuilder())
                }
            }
    }
}