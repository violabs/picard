package io.violabs.picard.domain.k8sResources.authentication.tokenRequest


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.authentication.BoundObjectReference
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestTest : SuccessBuildSim<TokenRequest, TokenRequest.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<TokenRequest, TokenRequest.Builder> {
            scenario {
                id = "minimum"
                given(TokenRequest.Builder())
                expected = TokenRequest()
            }

            scenario {
                id = "full"
                given(TokenRequest.Builder()) {
                    sharedMetadata()
                    spec {
                        audiences(PLACEHOLDER)
                        boundObjectRef {
                            apiVersion = PLACEHOLDER
                            kind = PLACEHOLDER
                            name = PLACEHOLDER
                            uid = PLACEHOLDER
                        }
                        expirationSeconds = 1
                    }

                    this.status {
                        expirationTimestamp = NOW
                        token = PLACEHOLDER
                    }
                }
                expected = TokenRequest(
                    metadata = METADATA,
                    spec = TokenRequest.Spec(
                        audiences = listOf(PLACEHOLDER),
                        boundObjectRef = BoundObjectReference(
                            apiVersion = PLACEHOLDER,
                            kind = PLACEHOLDER,
                            name = PLACEHOLDER,
                            uid = PLACEHOLDER
                        ),
                        expirationSeconds = 1
                    ),
                    status = TokenRequest.Status(
                        expirationTimestamp = NOW,
                        token = PLACEHOLDER
                    )
                )
            }
        }
    }
}