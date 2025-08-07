package io.violabs.picard.v2.resources.authentication.token.request


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestStatusTest : FailureBuildSim<TokenRequest.Status, TokenRequest.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<TokenRequest.Status, TokenRequest.Status.Builder> {
            requireScenario("expirationTimestamp") {
                given(TokenRequest.Status.Builder())
            }

            requireScenario("token") {
                given(TokenRequest.Status.Builder()) {
                    expirationTimestamp = NOW
                }
            }
        }
    }
}