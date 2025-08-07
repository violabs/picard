package io.violabs.picard.domain.k8sResources.cluster.lease.candidate


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
            requireScenario("leaseName") {
                given(LeaseCandidateSpecDslBuilder())
            }

            requireNotEmptyScenario("preferredStrategies") {
                given(LeaseCandidateSpecDslBuilder()) {
                    leaseName = PLACEHOLDER
                }
            }
        }
    }
}