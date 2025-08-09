package io.violabs.picard.v2.resources.authorization.review.access.subject.local


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.review.access.subject.AccessReviewTestConfig
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewSpec
import org.junit.jupiter.api.BeforeAll

class LocalSubjectAccessReviewTest : FullBuildSim<LocalSubjectAccessReview, LocalSubjectAccessReviewDslBuilder>() {
    companion object : AccessReviewTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LocalSubjectAccessReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LocalSubjectAccessReview, LocalSubjectAccessReviewDslBuilder> {
            scenario {
                id = "minimum"
                given(LocalSubjectAccessReviewDslBuilder()) {
                    spec {  }
                }
                expected = LocalSubjectAccessReview(
                    spec = SubjectAccessReviewSpec()
                )
            }

            scenario {
                id = "full"
                given(LocalSubjectAccessReviewDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec { sharedSubjectAccessReviewSpec() }

                    status { sharedSubjectAccessReviewStatus() }
                }
                expected = LocalSubjectAccessReview(
                    spec = sharedSubjectAccessReviewSpec(),
                    status = sharedSubjectAccessReviewStatus(),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LocalSubjectAccessReview, LocalSubjectAccessReviewDslBuilder> {
            requireScenario("spec") {
                given(LocalSubjectAccessReviewDslBuilder())
            }
        }
    }
}