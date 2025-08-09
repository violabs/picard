package io.violabs.picard.v2.resources.authentication.token.review

import io.violabs.picard.Common.sharedListMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenReviewListTest : FullBuildSim<TokenReviewList, TokenReviewListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenReviewListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<TokenReviewList, TokenReviewListDslBuilder> {
            scenario {
                id = "minimum"
                given(TokenReviewListDslBuilder()) {
                    items {
                        tokenReview {
                            spec {  }
                        }
                    }

                    metadata { sharedListMeta() }
                }
                expected = TokenReviewList(
                    items = listOf(TokenReview(spec = TokenReviewSpec())),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<TokenReviewList, TokenReviewListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(TokenReviewListDslBuilder())
            }
        }
    }
}