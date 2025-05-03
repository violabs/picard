package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConsumerReferenceTest : FailureBuildSim<ConsumerReference, ConsumerReference.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConsumerReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ConsumerReference, ConsumerReference.Builder> {
            requireScenario("name") {
                given(ConsumerReference.Builder())
            }

            requireScenario("resource") {
                given(ConsumerReference.Builder()) {
                    name = PLACEHOLDER
                }
            }

            requireScenario("uid") {
                given(ConsumerReference.Builder()) {
                    name = PLACEHOLDER
                    resource = PLACEHOLDER
                }
            }
        }
    }
}