package io.violabs.picard.domain.k8sResources.authorization.accessReview


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectAccessReviewTest : FullBuildSim<SelfSubjectAccessReview, SelfSubjectAccessReview.Builder>() {
    companion object : AccessReviewTest {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectAccessReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SelfSubjectAccessReview, SelfSubjectAccessReview.Builder> {
            scenario {
                id = "minimum"
                given(SelfSubjectAccessReview.Builder()) {
                    spec { }
                }
                expected = SelfSubjectAccessReview(
                    spec = SelfSubjectAccessReview.Spec()
                )
            }

            scenario {
                id = "full"
                given(SelfSubjectAccessReview.Builder()) {
                    spec {
                        nonResourceAttributes { sharedNonResourceAttributes() }
                        resourceAttributes { sharedResourceAttributes() }
                    }

                    // this is same as already tests [SubjectAccessReviewTest]
                    status { sharedSubjectAccessReviewStatus() }

                    sharedMetadata()
                }
                expected = SelfSubjectAccessReview(
                    spec = SelfSubjectAccessReview.Spec(
                        nonResourceAttributes = sharedNonResourceAttributes(),
                        resourceAttributes = sharedResourceAttributes()
                    ),
                    status = sharedSubjectAccessReviewStatus(),
                    metadata = METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SelfSubjectAccessReview, SelfSubjectAccessReview.Builder> {
            requireScenario("spec") {
                given(SelfSubjectAccessReview.Builder())
            }
        }
    }
}