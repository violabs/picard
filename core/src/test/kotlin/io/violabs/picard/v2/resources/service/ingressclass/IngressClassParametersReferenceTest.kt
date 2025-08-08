package io.violabs.picard.v2.resources.service.ingressclass


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressClassParametersReferenceTest :
    FailureBuildSim<IngressClassParametersReference, IngressClassParametersReferenceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressClassParametersReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<IngressClassParametersReference, IngressClassParametersReferenceDslBuilder> {
                requireScenario("kind") {
                    given(IngressClassParametersReferenceDslBuilder())
                }

                requireScenario("name") {
                    given(IngressClassParametersReferenceDslBuilder()) {
                        kind = PLACEHOLDER
                    }
                }
            }
    }
}