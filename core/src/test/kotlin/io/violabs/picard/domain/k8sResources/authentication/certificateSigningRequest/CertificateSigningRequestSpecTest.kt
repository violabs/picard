package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CertificateSigningRequestSpecTest :
    FailureBuildSim<CertificateSigningRequest.Spec, CertificateSigningRequest.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CertificateSigningRequestSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CertificateSigningRequest.Spec, CertificateSigningRequest.Spec.Builder> {
                requireNotEmptyScenario("request") {
                    given(CertificateSigningRequest.Spec.Builder())
                }

                requireScenario("signerName") {
                    given(CertificateSigningRequest.Spec.Builder()) {
                        request(0b1)
                    }
                }
            }
    }
}