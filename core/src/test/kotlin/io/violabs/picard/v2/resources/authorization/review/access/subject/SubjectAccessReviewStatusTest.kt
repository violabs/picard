package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SubjectAccessReviewStatusTest : FailureBuildSim<SubjectAccessReviewStatus, SubjectAccessReviewStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SubjectAccessReviewStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<SubjectAccessReviewStatus, SubjectAccessReviewStatusDslBuilder> {
                requireScenario("allowed") {
                    given(SubjectAccessReviewStatusDslBuilder())
                }
            }
    }
}