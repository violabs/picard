package io.violabs.picard.domain.k8sResources.cluster.lease.candidate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateTest : FullBuildSim<LeaseCandidate, LeaseCandidate.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseCandidate, LeaseCandidate.Builder> {
            scenario {
                id = "minimum"
                given(LeaseCandidate.Builder()) {
                    spec {
                        leaseName = PLACEHOLDER
                        preferredStrategies(PLACEHOLDER)
                    }
                }
                expected = LeaseCandidate(
                    spec = LeaseCandidate.Spec(
                        leaseName = PLACEHOLDER,
                        preferredStrategies = listOf(PLACEHOLDER)
                    )
                )
            }

            scenario {
                id = "full"
                given(LeaseCandidate.Builder()) {
                    spec {
                        leaseName = PLACEHOLDER
                        preferredStrategies(PLACEHOLDER)
                        binaryVersion = PLACEHOLDER
                        emulationVersion = PLACEHOLDER
                        pingTime = TIMESTAMP
                        renewTime = TIMESTAMP
                    }
                }
                expected = LeaseCandidate(
                    spec = LeaseCandidate.Spec(
                        leaseName = PLACEHOLDER,
                        preferredStrategies = listOf(PLACEHOLDER),
                        binaryVersion = PLACEHOLDER,
                        emulationVersion = PLACEHOLDER,
                        pingTime = TIMESTAMP,
                        renewTime = TIMESTAMP
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LeaseCandidate, LeaseCandidate.Builder> {
            requireScenario("spec") {
                given(LeaseCandidate.Builder())
            }
        }
    }
}