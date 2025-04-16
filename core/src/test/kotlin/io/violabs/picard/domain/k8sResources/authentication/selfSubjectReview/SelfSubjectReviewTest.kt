package io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.authentication.UserInfo
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectReviewTest : SuccessBuildSim<SelfSubjectReview, SelfSubjectReview.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectReviewTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<SelfSubjectReview, SelfSubjectReview.Builder> {
            scenario {
                id = "minimum"
                given(SelfSubjectReview.Builder())
                expected = SelfSubjectReview()
            }

            scenario {
                id = "full"
                given(SelfSubjectReview.Builder()) {
                    sharedMetadata()

                    status {
                        userInfo {
                            extra(PLACEHOLDER to listOf(PLACEHOLDER))
                            groups(PLACEHOLDER)
                            uid = PLACEHOLDER
                            username = PLACEHOLDER
                        }
                    }
                }
                expected = SelfSubjectReview(
                    metadata = METADATA,
                    status = SelfSubjectReview.Status(
                        userInfo = UserInfo(
                            extra = mapOf(PLACEHOLDER to listOf(PLACEHOLDER)),
                            groups = listOf(PLACEHOLDER),
                            uid = PLACEHOLDER,
                            username = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}