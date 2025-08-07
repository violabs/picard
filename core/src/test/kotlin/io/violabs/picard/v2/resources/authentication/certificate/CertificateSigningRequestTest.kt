package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestTest :
    FullBuildSim<CertificateSigningRequest, CertificateSigningRequestDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CertificateSigningRequest, CertificateSigningRequestDslBuilder> {
                scenario {
                    id = "minimum"
                    given(CertificateSigningRequestDslBuilder()) {
                        spec {
                            request(0b1, 0b01)
                            signerName = PLACEHOLDER
                        }
                    }
                    expected = CertificateSigningRequest(
                        spec = CertificateSigningRequestSpec(
                            request = BYTES,
                            signerName = PLACEHOLDER
                        )
                    )
                }

                scenario {
                    id = "full"
                    given(CertificateSigningRequestDslBuilder()) {
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
                                    status = CertificateSigningRequestConditionStatus.True
                                    type = CertificateSigningRequestCondition.Type.Approved
                                    lastTransitionTime = NOW
                                    lastUpdateTime = NOW
                                    message = PLACEHOLDER
                                    reason = PLACEHOLDER
                                }
                            }
                        }
                    }
                    expected = CertificateSigningRequest(
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
                                    status = CertificateSigningRequestConditionStatus.True,
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
            possibilities<CertificateSigningRequest, CertificateSigningRequestDslBuilder> {
                requireScenario("spec") {
                    given(CertificateSigningRequestDslBuilder())
                }
            }
    }
}