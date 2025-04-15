package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestListTest :
    FullBuildSim<CertificateSigningRequestList, CertificateSigningRequestList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CertificateSigningRequestList, CertificateSigningRequestList.Builder> {
                scenario {
                    id = "minimum"
                    description = "adding metadata for coverage"
                    given(CertificateSigningRequestList.Builder()) {
                        items {
                            request {
                                spec {
                                    request(0b1)
                                    signerName = "test"
                                }
                            }
                        }

                        metadata {  }
                    }
                    expected = CertificateSigningRequestList(
                        items = listOf(
                            CertificateSigningRequest(
                                spec = CertificateSigningRequest.Spec(
                                    request = listOf(0b1),
                                    signerName = "test"
                                )
                            )
                        ),
                        metadata = ListMeta()
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<CertificateSigningRequestList, CertificateSigningRequestList.Builder> {
                requireNotEmptyScenario("items") {
                    given(CertificateSigningRequestList.Builder())
                }
            }
    }
}