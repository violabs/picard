package io.violabs.picard.v2.resources.authentication.token.review


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenReviewTest : FullBuildSim<TokenReviewV2, TokenReviewV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<TokenReviewV2, TokenReviewV2DslBuilder> {
            scenario {
                id = "minimum"
                given(TokenReviewV2DslBuilder()) {
                    spec {  }
                }
                expected = TokenReviewV2(
                    spec = TokenReviewSpec()
                )
            }

            scenario {
                id = "full"
                given(TokenReviewV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        audiences(PLACEHOLDER)
                        token = PLACEHOLDER
                    }

                    this.status {
                        audiences(PLACEHOLDER)
                        authenticated()
                        error = PLACEHOLDER
                        user {
                            extra(PLACEHOLDER to listOf(PLACEHOLDER))
                            groups(PLACEHOLDER)
                            uid = PLACEHOLDER
                            username = PLACEHOLDER
                        }
                    }
                }
                expected = TokenReviewV2(
                    metadata = Common.OBJECT_META,
                    spec = TokenReviewSpec(
                        audiences = listOf(PLACEHOLDER),
                        token = PLACEHOLDER
                    ),
                    status = TokenReviewStatus(
                        audiences = listOf(PLACEHOLDER),
                        authenticated = true,
                        error = PLACEHOLDER,
                        user = UserInfo(
                            extra = mapOf(PLACEHOLDER to listOf(PLACEHOLDER)),
                            groups = listOf(PLACEHOLDER),
                            uid = PLACEHOLDER,
                            username = PLACEHOLDER
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<TokenReviewV2, TokenReviewV2DslBuilder> {
            requireScenario("spec") {
                given(TokenReviewV2DslBuilder())
            }
        }
    }
}