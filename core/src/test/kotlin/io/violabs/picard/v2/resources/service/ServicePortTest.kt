package io.violabs.picard.v2.resources.service


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServicePortTest : FullBuildSim<ServicePort, ServicePortDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServicePortTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServicePort, ServicePortDslBuilder> {
            scenario {
                id = "minimum"
                given(ServicePortDslBuilder()) {
                    port = 1
                    targetPort = PLACEHOLDER
                }
                expected = ServicePort(
                    port = 1,
                    targetPort = PLACEHOLDER
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServicePort, ServicePortDslBuilder> {
            requireScenario("port") {
                given(ServicePortDslBuilder())
            }
        }
    }
}