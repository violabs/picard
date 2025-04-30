package io.violabs.picard.domain.k8sResources.service.ingressClass


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressClassParametersReferenceTest :
    FailureBuildSim<IngressClassParametersReference, IngressClassParametersReference.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressClassParametersReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<IngressClassParametersReference, IngressClassParametersReference.Builder> {
                requireScenario("kind") {
                    given(IngressClassParametersReference.Builder())
                }

                requireScenario("name") {
                    given(IngressClassParametersReference.Builder()) {
                        kind = PLACEHOLDER
                    }
                }
            }
    }
}