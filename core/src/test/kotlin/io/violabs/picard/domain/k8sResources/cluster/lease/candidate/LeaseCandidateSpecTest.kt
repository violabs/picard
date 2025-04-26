package io.violabs.picard.domain.k8sResources.cluster.lease.candidate


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateSpecTest : FailureBuildSim<LeaseCandidate.Spec, LeaseCandidate.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LeaseCandidate.Spec, LeaseCandidate.Spec.Builder> {
            requireScenario("leaseName") {
                given(LeaseCandidate.Spec.Builder())
            }

            requireNotEmptyScenario("preferredStrategies") {
                given(LeaseCandidate.Spec.Builder()) {
                    leaseName = PLACEHOLDER
                }
            }
        }
    }
}