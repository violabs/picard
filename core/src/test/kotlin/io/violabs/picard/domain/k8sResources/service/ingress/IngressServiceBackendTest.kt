package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressServiceBackendTest : FailureBuildSim<IngressServiceBackend, IngressServiceBackendDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressServiceBackendTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<IngressServiceBackend, IngressServiceBackendDslBuilder> {
            requireScenario("name") {
                given(IngressServiceBackendDslBuilder())
            }
        }
    }
}