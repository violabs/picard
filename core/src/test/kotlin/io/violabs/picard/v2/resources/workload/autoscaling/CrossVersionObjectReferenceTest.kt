package io.violabs.picard.v2.resources.workload.autoscaling

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CrossVersionObjectReferenceTest :
    FailureBuildSim<CrossVersionObjectReference, CrossVersionObjectReferenceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CrossVersionObjectReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CrossVersionObjectReference, CrossVersionObjectReferenceDslBuilder> {
                requireScenario("kind") {
                    given(CrossVersionObjectReferenceDslBuilder())
                }

                requireScenario("name") {
                    given(CrossVersionObjectReferenceDslBuilder()) {
                        kind = PLACEHOLDER
                    }
                }
            }
    }
}