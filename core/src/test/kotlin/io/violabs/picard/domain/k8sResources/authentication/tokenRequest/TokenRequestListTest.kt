package io.violabs.picard.domain.k8sResources.authentication.tokenRequest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestListTest : FullBuildSim<TokenRequestList, TokenRequestList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<TokenRequestList, TokenRequestList.Builder> {
            scenario {
                id = "minimum"
                description = "with shared metadata"
                given(TokenRequestList.Builder()) {
                    items {
                        request {

                        }
                    }

                    sharedMetadata()
                }
                expected = TokenRequestList(
                    items = listOf(TokenRequest()),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<TokenRequestList, TokenRequestList.Builder> {
            requireNotEmptyScenario("items") {
                given(TokenRequestList.Builder())
            }
        }
    }
}