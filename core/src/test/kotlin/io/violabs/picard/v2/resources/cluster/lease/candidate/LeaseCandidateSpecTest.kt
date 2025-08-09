package io.violabs.picard.v2.resources.cluster.lease.candidate


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateSpecTest : FailureBuildSim<LeaseCandidateSpec, LeaseCandidateSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LeaseCandidateSpec, LeaseCandidateSpecDslBuilder> {
            requireScenario("binaryVersion") {
                given(LeaseCandidateSpecDslBuilder())
            }

            requireScenario("leaseName") {
                given(LeaseCandidateSpecDslBuilder()) {
                    binaryVersion = PLACEHOLDER
                }
            }

//            requireNotEmptyScenario("strategy") {
            requireScenario("strategy") {
                given(LeaseCandidateSpecDslBuilder()) {
                    leaseName = PLACEHOLDER
                    binaryVersion = PLACEHOLDER
                }
            }
        }
    }
}