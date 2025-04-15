package io.violabs.picard.domain.k8sResources.authentication.tokenReview


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenReviewListTest : FullBuildSim<TokenReviewList, TokenReviewList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenReviewListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<TokenReviewList, TokenReviewList.Builder> {
            scenario {
                id = "minimum"
                given(TokenReviewList.Builder()) {
                    items {
                        review {
                            spec {  }
                        }
                    }

                    sharedMetadata()
                }
                expected = TokenReviewList(
                    items = listOf(TokenReview(spec = TokenReview.Spec())),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<TokenReviewList, TokenReviewList.Builder> {
            requireNotEmptyScenario("items") {
                given(TokenReviewList.Builder())
            }
        }
    }
}