package io.violabs.picard.v2.resources.authorization.review.access.subject.local


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.review.access.subject.AccessReviewTest
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewSpec
import org.junit.jupiter.api.BeforeAll

class LocalSubjectAccessReviewTest : FullBuildSim<LocalSubjectAccessReviewV2, LocalSubjectAccessReviewV2DslBuilder>() {
    companion object : AccessReviewTest {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LocalSubjectAccessReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LocalSubjectAccessReviewV2, LocalSubjectAccessReviewV2DslBuilder> {
            scenario {
                id = "minimum"
                given(LocalSubjectAccessReviewV2DslBuilder()) {
                    spec {  }
                }
                expected = LocalSubjectAccessReviewV2(
                    spec = SubjectAccessReviewSpec()
                )
            }

            scenario {
                id = "full"
                given(LocalSubjectAccessReviewV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec { sharedSubjectAccessReviewSpec() }

                    status { sharedSubjectAccessReviewStatus() }
                }
                expected = LocalSubjectAccessReviewV2(
                    spec = sharedSubjectAccessReviewSpec(),
                    status = sharedSubjectAccessReviewStatus(),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LocalSubjectAccessReviewV2, LocalSubjectAccessReviewV2DslBuilder> {
            requireScenario("spec") {
                given(LocalSubjectAccessReviewV2DslBuilder())
            }
        }
    }
}