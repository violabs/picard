package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TypedLocalObjectReferenceTest : FailureBuildSim<TypedLocalObjectReference, TypedLocalObjectReference.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TypedLocalObjectReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<TypedLocalObjectReference, TypedLocalObjectReference.Builder> {
                requireScenario("kind") {
                    given(TypedLocalObjectReference.Builder())
                }

                requireScenario("name") {
                    given(TypedLocalObjectReference.Builder()) {
                        kind = PLACEHOLDER
                    }
                }
            }
    }
}