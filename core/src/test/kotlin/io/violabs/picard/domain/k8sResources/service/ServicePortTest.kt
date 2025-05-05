package io.violabs.picard.domain.k8sResources.service


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServicePortTest : FullBuildSim<ServicePort, ServicePort.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServicePortTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServicePort, ServicePort.Builder> {
            scenario {
                id = "minimum"
                given(ServicePort.Builder()) {
                    port = 1
                    targetPort("1")
                }
                expected = ServicePort(
                    port = 1,
                    targetPort = IntOrString(str = "1"),
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServicePort, ServicePort.Builder> {
            requireScenario("port") {
                given(ServicePort.Builder())
            }
        }
    }
}