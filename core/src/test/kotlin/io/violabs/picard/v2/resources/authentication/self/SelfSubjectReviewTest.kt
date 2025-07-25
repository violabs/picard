package io.violabs.picard.v2.resources.authentication.self


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectReviewTest : SuccessBuildSim<SelfSubjectReviewV2, SelfSubjectReviewV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectReviewTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<SelfSubjectReviewV2, SelfSubjectReviewV2DslBuilder> {
            scenario {
                id = "minimum"
                given(SelfSubjectReviewV2DslBuilder())
                expected = SelfSubjectReviewV2()
            }

            scenario {
                id = "full"
                given(SelfSubjectReviewV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    status {
                        userInfo {
                            extra(PLACEHOLDER to listOf(PLACEHOLDER))
                            groups(PLACEHOLDER)
                            uid = PLACEHOLDER
                            username = PLACEHOLDER
                        }
                    }
                }
                expected = SelfSubjectReviewV2(
                    metadata = Common.OBJECT_META,
                    status = SelfSubjectReviewStatus(
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