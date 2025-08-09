package io.violabs.picard.v2.resources.cluster.ipaddress


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ParentReferenceTest : FailureBuildSim<ParentReference, ParentReferenceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ParentReferenceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ParentReference, ParentReferenceDslBuilder> {
            requireScenario("name") {
                given(ParentReferenceDslBuilder())
            }

            requireScenario("resource") {
                given(ParentReferenceDslBuilder()) {
                    name = PLACEHOLDER
                }
            }
        }
    }
}