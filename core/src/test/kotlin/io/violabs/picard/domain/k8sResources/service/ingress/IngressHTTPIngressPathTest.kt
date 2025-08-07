package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressHTTPIngressPathTest : FailureBuildSim<IngressHTTPIngressPath, IngressHTTPIngressPathDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressHTTPIngressPathTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<IngressHTTPIngressPath, IngressHTTPIngressPathDslBuilder> {
            requireScenario("backend") {
                given(IngressHTTPIngressPathDslBuilder())
            }

            requireScenario("pathType") {
                given(IngressHTTPIngressPathDslBuilder()) {
                    backend {

                    }
                }
            }
        }
    }
}