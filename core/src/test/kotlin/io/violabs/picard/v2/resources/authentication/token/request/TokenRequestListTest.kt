package io.violabs.picard.v2.resources.authentication.token.request


import io.violabs.picard.Common.sharedListMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestListTest : FullBuildSim<TokenRequestList, TokenRequestListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<TokenRequestList, TokenRequestListDslBuilder> {
            scenario {
                id = "minimum"
                description = "with shared metadata"
                given(TokenRequestListDslBuilder()) {
                    items {
                        tokenRequest {
                            spec { audiences(PLACEHOLDER) }
                        }
                    }

                    metadata { sharedListMeta() }
                }
                expected = TokenRequestList(
                    items = listOf(TokenRequest(spec = TokenRequestSpec(audiences = PLACEHOLDER_LIST))),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<TokenRequestList, TokenRequestListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(TokenRequestListDslBuilder())
            }
        }
    }
}