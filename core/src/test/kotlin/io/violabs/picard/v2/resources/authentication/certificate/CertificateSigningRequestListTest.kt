package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestListTest :
    FullBuildSim<CertificateSigningRequestList, CertificateSigningRequestListV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CertificateSigningRequestList, CertificateSigningRequestListV2DslBuilder> {
                scenario {
                    id = "minimum"
                    description = "adding metadata for coverage"
                    given(CertificateSigningRequestListV2DslBuilder()) {
                        items {

                        }

//                        metadata { }
                    }
                    expected = CertificateSigningRequestList(
                        items = listOf(
                            CertificateSigningRequest(
                                spec = CertificateSigningRequestSpec(
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
            possibilities<CertificateSigningRequestList, CertificateSigningRequestListV2DslBuilder> {
                requireNotEmptyScenario("items") {
                    given(CertificateSigningRequestListV2DslBuilder())
                }
            }
    }
}