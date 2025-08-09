package io.violabs.picard.v2.resources.authentication.token.request


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestSpecTest : FailureBuildSim<TokenRequestSpec, TokenRequestSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<TokenRequestSpec, TokenRequestSpecDslBuilder> {
            requireNotEmptyScenario("audiences") {
                given(TokenRequestSpecDslBuilder())
            }
        }
    }
}