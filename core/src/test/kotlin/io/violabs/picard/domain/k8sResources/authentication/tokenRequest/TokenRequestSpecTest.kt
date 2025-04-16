package io.violabs.picard.domain.k8sResources.authentication.tokenRequest


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestSpecTest : FailureBuildSim<TokenRequest.Spec, TokenRequest.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<TokenRequest.Spec, TokenRequest.Spec.Builder> {
            requireNotEmptyScenario("audiences") {
                given(TokenRequest.Spec.Builder())
            }
        }
    }
}