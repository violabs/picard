package io.violabs.picard.domain.k8sResources.authorization.accessReview


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LocalSubjectAccessReviewTest : FullBuildSim<LocalSubjectAccessReview, LocalSubjectAccessReview.Builder>() {
    companion object : AccessReviewTest {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LocalSubjectAccessReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LocalSubjectAccessReview, LocalSubjectAccessReview.Builder> {
            scenario {
                id = "minimum"
                given(LocalSubjectAccessReview.Builder()) {
                    spec {  }
                }
                expected = LocalSubjectAccessReview(
                    spec = SubjectAccessReview.Spec()
                )
            }

            scenario {
                id = "full"
                given(LocalSubjectAccessReview.Builder()) {
                    spec { sharedSubjectAccessReviewSpec() }

                    status { sharedSubjectAccessReviewStatus() }

                    sharedMetadata()
                }
                expected = LocalSubjectAccessReview(
                    spec = sharedSubjectAccessReviewSpec(),
                    status = sharedSubjectAccessReviewStatus(),
                    metadata = METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LocalSubjectAccessReview, LocalSubjectAccessReview.Builder> {
            requireScenario("spec") {
                given(LocalSubjectAccessReview.Builder())
            }
        }
    }
}