package io.violabs.picard.v2.resources.authorization.review.access.subject


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SubjectAccessReviewTest : FullBuildSim<SubjectAccessReview, SubjectAccessReviewDslBuilder>() {
    companion object : AccessReviewTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SubjectAccessReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SubjectAccessReview, SubjectAccessReviewDslBuilder> {
            scenario {
                id = "minimum"
                given(SubjectAccessReviewDslBuilder()) {
                    spec { }
                }
                expected = SubjectAccessReview(
                    spec = SubjectAccessReviewSpec()
                )
            }

            scenario {
                id = "full"
                given(SubjectAccessReviewDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec { sharedSubjectAccessReviewSpec() }

                    this.status { sharedSubjectAccessReviewStatus() }
                }
                expected = SubjectAccessReview(
                    metadata = Common.OBJECT_META,
                    spec = sharedSubjectAccessReviewSpec(),
                    status = sharedSubjectAccessReviewStatus()
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SubjectAccessReview, SubjectAccessReviewDslBuilder> {
            requireScenario("spec") {
                given(SubjectAccessReviewDslBuilder())
            }
        }
    }
}