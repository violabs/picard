package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestSpecTest :
    FailureBuildSim<CertificateSigningRequestSpec, CertificateSigningRequestSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CertificateSigningRequestSpec, CertificateSigningRequestSpecDslBuilder> {
                requireNotEmptyScenario("request") {
                    given(CertificateSigningRequestSpecDslBuilder())
                }

                requireScenario("signerName") {
                    given(CertificateSigningRequestSpecDslBuilder()) {
                        request(0b1)
                    }
                }
            }
    }
}