package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.SigningRequestCondition
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestTest : FullBuildSim<CertificateSigningRequest, CertificateSigningRequest.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CertificateSigningRequest, CertificateSigningRequest.Builder> {
                scenario {
                    id = "minimum"
                    given(CertificateSigningRequest.Builder()) {
                        spec {
                            request(0b1, 0b01)
                            signerName = PLACEHOLDER
                        }
                    }
                    expected = CertificateSigningRequest(
                        spec = CertificateSigningRequest.Spec(
                            request = BYTES,
                            signerName = PLACEHOLDER
                        )
                    )
                }

                scenario {
                    id = "full"
                    given(CertificateSigningRequest.Builder()) {
                        sharedMetadata()

                        spec {
                            request(0b1, 0b01)
                            signerName = PLACEHOLDER
                            expirationSeconds = 1
                            extra(PLACEHOLDER to listOf(PLACEHOLDER))
                            groups(PLACEHOLDER)
                            uid = PLACEHOLDER
                            usages(PLACEHOLDER)
                            username = PLACEHOLDER
                        }

                        status {
                            certificate(0b1, 0b01)
                            conditions {
                                condition {
                                    status = BooleanType.True
                                    type = PLACEHOLDER
                                    lastTransitionTime = NOW
                                    lastUpdateTime = NOW
                                    message = PLACEHOLDER
                                    reason = PLACEHOLDER
                                }
                            }
                        }
                    }
                    expected = CertificateSigningRequest(
                        metadata = METADATA,

                        spec = CertificateSigningRequest.Spec(
                            request = listOf(0b1, 0b01),
                            signerName = PLACEHOLDER,
                            expirationSeconds = 1,
                            extra = mutableMapOf(PLACEHOLDER to listOf(PLACEHOLDER)),
                            groups = listOf(PLACEHOLDER),
                            uid = PLACEHOLDER,
                            usages = listOf(PLACEHOLDER),
                            username = PLACEHOLDER
                        ),

                        status = CertificateSigningRequest.Status(
                            certificate = BYTES,
                            conditions = listOf(
                                SigningRequestCondition(
                                    status = BooleanType.True,
                                    type = PLACEHOLDER,
                                    lastTransitionTime = NOW,
                                    lastUpdateTime = NOW,
                                    message = PLACEHOLDER,
                                    reason = PLACEHOLDER
                                )
                            )
                        )
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<CertificateSigningRequest, CertificateSigningRequest.Builder> {
                requireScenario("spec") {
                    given(CertificateSigningRequest.Builder())
                }
            }
    }
}