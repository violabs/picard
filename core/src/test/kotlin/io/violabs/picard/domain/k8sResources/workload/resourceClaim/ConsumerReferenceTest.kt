package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConsumerReferenceTest : FailureBuildSim<ConsumerReference, ConsumerReferenceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConsumerReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ConsumerReference, ConsumerReferenceDslBuilder> {
            requireScenario("name") {
                given(ConsumerReferenceDslBuilder())
            }

            requireScenario("resource") {
                given(ConsumerReferenceDslBuilder()) {
                    name = PLACEHOLDER
                }
            }

            requireScenario("uid") {
                given(ConsumerReferenceDslBuilder()) {
                    name = PLACEHOLDER
                    resource = PLACEHOLDER
                }
            }
        }
    }
}