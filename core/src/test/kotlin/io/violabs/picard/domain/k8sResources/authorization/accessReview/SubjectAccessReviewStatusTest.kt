package io.violabs.picard.domain.k8sResources.authorization.accessReview


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SubjectAccessReviewStatusTest : FailureBuildSim<SubjectAccessReview.Status, SubjectAccessReview.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SubjectAccessReviewStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<SubjectAccessReview.Status, SubjectAccessReview.Status.Builder> {
                requireScenario("allowed") {
                    given(SubjectAccessReview.Status.Builder())
                }
            }
    }
}