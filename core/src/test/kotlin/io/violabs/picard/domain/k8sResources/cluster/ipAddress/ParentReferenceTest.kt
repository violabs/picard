package io.violabs.picard.domain.k8sResources.cluster.ipAddress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ParentReferenceTest : FailureBuildSim<ParentReference, ParentReference.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ParentReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ParentReference, ParentReference.Builder> {
            requireScenario("name") {
                given(ParentReference.Builder())
            }

            requireScenario("resource") {
                given(ParentReference.Builder()) {
                    name = PLACEHOLDER
                }
            }
        }
    }
}