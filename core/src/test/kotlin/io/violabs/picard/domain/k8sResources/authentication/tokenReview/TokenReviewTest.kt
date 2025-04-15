package io.violabs.picard.domain.k8sResources.authentication.tokenReview


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.authentication.UserInfo
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenReviewTest : FullBuildSim<TokenReview, TokenReview.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<TokenReview, TokenReview.Builder> {
            scenario {
                id = "minimum"
                given(TokenReview.Builder()) {
                    spec {  }
                }
                expected = TokenReview(
                    spec = TokenReview.Spec()
                )
            }

            scenario {
                id = "full"
                given(TokenReview.Builder()) {
                    sharedMetadata()

                    spec {
                        audiences(PLACEHOLDER)
                        token = PLACEHOLDER
                    }

                    status {
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
                expected = TokenReview(
                    metadata = METADATA,
                    spec = TokenReview.Spec(
                        audiences = listOf(PLACEHOLDER),
                        token = PLACEHOLDER
                    ),
                    status = TokenReview.Status(
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

        private val FAILURE_POSSIBILITIES = possibilities<TokenReview, TokenReview.Builder> {
            requireScenario("spec") {
                given(TokenReview.Builder())
            }
        }
    }
}