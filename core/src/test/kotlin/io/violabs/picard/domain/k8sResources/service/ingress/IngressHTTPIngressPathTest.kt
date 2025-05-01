package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressHTTPIngressPathTest : FailureBuildSim<IngressHTTPIngressPath, IngressHTTPIngressPath.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressHTTPIngressPathTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<IngressHTTPIngressPath, IngressHTTPIngressPath.Builder> {
            requireScenario("backend") {
                given(IngressHTTPIngressPath.Builder())
            }

            requireScenario("pathType") {
                given(IngressHTTPIngressPath.Builder()) {
                    backend {

                    }
                }
            }
        }
    }
}