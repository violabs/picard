package io.violabs.picard.v2.resources.authentication.token.request


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TokenRequestTest : SuccessBuildSim<TokenRequest, TokenRequestDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TokenRequestTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<TokenRequest, TokenRequestDslBuilder> {
            scenario {
                id = "minimum"
                given(TokenRequestDslBuilder()) {
                    spec {
                        audiences(PLACEHOLDER)
                    }
                }
                expected = TokenRequest(
                    spec = TokenRequestSpec(audiences = PLACEHOLDER_LIST),
                )
            }

            scenario {
                id = "full"
                given(TokenRequestDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        audiences(PLACEHOLDER)
                        boundObjectRef {
                            apiVersion = KAPIVersion.V1
                            kind = BoundObjectReference.Kind.Pod
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
                    metadata = Common.OBJECT_META,
                    spec = TokenRequestSpec(
                        audiences = listOf(PLACEHOLDER),
                        boundObjectRef = BoundObjectReference(
                            apiVersion = KAPIVersion.V1,
                            kind = BoundObjectReference.Kind.Pod,
                            name = PLACEHOLDER,
                            uid = PLACEHOLDER
                        ),
                        expirationSeconds = 1
                    ),
                    status = TokenRequestStatus(
                        expirationTimestamp = NOW,
                        token = PLACEHOLDER
                    )
                )
            }
        }
    }
}