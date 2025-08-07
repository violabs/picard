package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TypedLocalObjectReferenceTest : FailureBuildSim<TypedLocalObjectReference, TypedLocalObjectReferenceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TypedLocalObjectReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<TypedLocalObjectReference, TypedLocalObjectReferenceDslBuilder> {
                requireScenario("kind") {
                    given(TypedLocalObjectReferenceDslBuilder())
                }

                requireScenario("name") {
                    given(TypedLocalObjectReferenceDslBuilder()) {
                        kind = PLACEHOLDER
                    }
                }
            }
    }
}