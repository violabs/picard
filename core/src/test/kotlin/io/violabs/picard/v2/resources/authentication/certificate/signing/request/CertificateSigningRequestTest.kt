package io.violabs.picard.v2.resources.authentication.certificate.signing.request

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestTest :
    FullBuildSim<CertificateSigningRequestV2, CertificateSigningRequestV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CertificateSigningRequestV2, CertificateSigningRequestV2DslBuilder> {
                scenario {
                    id = "minimum"
                    given(CertificateSigningRequestV2DslBuilder()) {
                        spec {
                            request(0b1, 0b01)
                            signerName = PLACEHOLDER
                        }
                    }
                    expected = CertificateSigningRequestV2(
                        spec = CertificateSigningRequestSpec(
                            request = BYTES,
                            signerName = PLACEHOLDER
                        )
                    )
                }

                scenario {
                    id = "full"
                    given(CertificateSigningRequestV2DslBuilder()) {
                        metadata {
                            sharedObjectMeta()
                        }

                        spec {
                            request(0b1, 0b01)
                            signerName = PLACEHOLDER
                            expirationSeconds = 1
                            extra(PLACEHOLDER to listOf(PLACEHOLDER))
                            groups(PLACEHOLDER)
                            uid = PLACEHOLDER
                            usages(UsageType.DigitalSignature)
                            username = PLACEHOLDER
                        }

                        this.status {
                            certificate(0b1, 0b01)
                            conditions {
                                certificateSigningRequestCondition {
                                    status = CertificateSigningRequestCondition.Status.True
                                    type = CertificateSigningRequestCondition.Type.Approved
                                    lastTransitionTime = NOW
                                    lastUpdateTime = NOW
                                    message = PLACEHOLDER
                                    reason = PLACEHOLDER
                                }
                            }
                        }
                    }
                    expected = CertificateSigningRequestV2(
                        metadata = Common.OBJECT_META,

                        spec = CertificateSigningRequestSpec(
                            request = listOf(0b1, 0b01),
                            signerName = PLACEHOLDER,
                            expirationSeconds = 1,
                            extra = mutableMapOf(PLACEHOLDER to listOf(PLACEHOLDER)),
                            groups = listOf(PLACEHOLDER),
                            uid = PLACEHOLDER,
                            usages = listOf(UsageType.DigitalSignature),
                            username = PLACEHOLDER
                        ),

                        status = CertificateSigningRequestStatus(
                            certificate = BYTES,
                            conditions = listOf(
                                CertificateSigningRequestCondition(
                                    status = CertificateSigningRequestCondition.Status.True,
                                    type = CertificateSigningRequestCondition.Type.Approved,
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
            possibilities<CertificateSigningRequestV2, CertificateSigningRequestV2DslBuilder> {
                requireScenario("spec") {
                    given(CertificateSigningRequestV2DslBuilder())
                }
            }
    }
}