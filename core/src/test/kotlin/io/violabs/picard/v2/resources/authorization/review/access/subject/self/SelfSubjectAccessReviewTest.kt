package io.violabs.picard.v2.resources.authorization.review.access.subject.self


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.review.access.subject.AccessReviewTest
import org.junit.jupiter.api.BeforeAll

class SelfSubjectAccessReviewTest : FullBuildSim<SelfSubjectAccessReviewV2, SelfSubjectAccessReviewV2DslBuilder>() {
    companion object : AccessReviewTest {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectAccessReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SelfSubjectAccessReviewV2, SelfSubjectAccessReviewV2DslBuilder> {
            scenario {
                id = "minimum"
                given(SelfSubjectAccessReviewV2DslBuilder()) {
                    spec { }
                }
                expected = SelfSubjectAccessReviewV2(
                    spec = SelfSubjectAccessReviewSpec()
                )
            }

            scenario {
                id = "full"
                given(SelfSubjectAccessReviewV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        nonResourceAttributes { sharedNonResourceAttributes() }
                        resourceAttributes { sharedResourceAttributes() }
                    }

                    // this is same as already tests [SubjectAccessReviewTest]
                    status { sharedSubjectAccessReviewStatus() }
                }
                expected = SelfSubjectAccessReviewV2(
                    spec = SelfSubjectAccessReviewSpec(
                        nonResourceAttributes = sharedNonResourceAttributes(),
                        resourceAttributes = sharedResourceAttributes()
                    ),
                    status = sharedSubjectAccessReviewStatus(),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SelfSubjectAccessReviewV2, SelfSubjectAccessReviewV2DslBuilder> {
            requireScenario("spec") {
                given(SelfSubjectAccessReviewV2DslBuilder())
            }
        }
    }
}