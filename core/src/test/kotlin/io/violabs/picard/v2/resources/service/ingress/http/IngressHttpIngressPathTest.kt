package io.violabs.picard.v2.resources.service.ingress.http


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressHttpIngressPathTest : FailureBuildSim<HttpIngressPath, HttpIngressPathDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressHttpIngressPathTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<HttpIngressPath, HttpIngressPathDslBuilder> {
            requireScenario("backend") {
                given(HttpIngressPathDslBuilder())
            }

            requireScenario("pathType") {
                given(HttpIngressPathDslBuilder()) {
                    backend {

                    }
                }
            }
        }
    }
}