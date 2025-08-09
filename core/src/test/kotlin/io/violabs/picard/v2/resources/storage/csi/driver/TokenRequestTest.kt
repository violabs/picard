package io.violabs.picard.v2.resources.storage.csi.driver

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestTest : FailureBuildSim<TokenRequest, TokenRequestDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<TokenRequest, TokenRequestDslBuilder> {
            requireScenario("audience") {
                given(TokenRequestDslBuilder())
            }
        }
    }
}