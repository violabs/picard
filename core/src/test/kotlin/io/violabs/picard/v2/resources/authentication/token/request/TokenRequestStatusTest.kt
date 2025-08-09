package io.violabs.picard.v2.resources.authentication.token.request


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestStatusTest : FailureBuildSim<TokenRequestStatus, TokenRequestStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<TokenRequestStatus, TokenRequestStatusDslBuilder> {
            requireScenario("expirationTimestamp") {
                given(TokenRequestStatusDslBuilder())
            }

            requireScenario("token") {
                given(TokenRequestStatusDslBuilder()) {
                    expirationTimestamp = NOW
                }
            }
        }
    }
}