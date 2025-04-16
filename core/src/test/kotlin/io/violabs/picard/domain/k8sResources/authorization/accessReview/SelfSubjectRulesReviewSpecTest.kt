package io.violabs.picard.domain.k8sResources.authorization.accessReview


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectRulesReviewSpecTest :
    FailureBuildSim<SelfSubjectRulesReview.Spec, SelfSubjectRulesReview.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectRulesReviewSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<SelfSubjectRulesReview.Spec, SelfSubjectRulesReview.Spec.Builder> {
                requireScenario("namespace") {
                    given(SelfSubjectRulesReview.Spec.Builder())
                }
            }
    }
}