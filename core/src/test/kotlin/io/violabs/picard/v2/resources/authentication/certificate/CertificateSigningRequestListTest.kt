package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestListTest :
    FullBuildSim<CertificateSigningRequestList, CertificateSigningRequestListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CertificateSigningRequestList, CertificateSigningRequestListDslBuilder> {
                scenario {
                    id = "minimum"
                    description = "adding metadata for coverage"
                    given(CertificateSigningRequestListDslBuilder()) {
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
            possibilities<CertificateSigningRequestList, CertificateSigningRequestListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(CertificateSigningRequestListDslBuilder())
                }
            }
    }
}