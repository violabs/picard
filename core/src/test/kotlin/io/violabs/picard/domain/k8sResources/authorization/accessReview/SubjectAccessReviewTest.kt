package io.violabs.picard.domain.k8sResources.authorization.accessReview


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SubjectAccessReviewTest : FullBuildSim<SubjectAccessReview, SubjectAccessReview.Builder>() {
    companion object : AccessReviewTest {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SubjectAccessReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SubjectAccessReview, SubjectAccessReview.Builder> {
            scenario {
                id = "minimum"
                given(SubjectAccessReview.Builder()) {
                    spec { }
                }
                expected = SubjectAccessReview(
                    spec = SubjectAccessReview.Spec()
                )
            }

            scenario {
                id = "full"
                given(SubjectAccessReview.Builder()) {
                    sharedMetadata()

                    spec { sharedSubjectAccessReviewSpec() }

                    this.status { sharedSubjectAccessReviewStatus() }
                }
                expected = SubjectAccessReview(
                    metadata = METADATA,
                    spec = sharedSubjectAccessReviewSpec(),
                    status = sharedSubjectAccessReviewStatus()
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SubjectAccessReview, SubjectAccessReview.Builder> {
            requireScenario("spec") {
                given(SubjectAccessReview.Builder())
            }
        }
    }
}