package io.violabs.picard.domain.k8sResources.cluster.lease.candidate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateTest : FullBuildSim<LeaseCandidate, LeaseCandidateDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseCandidate, LeaseCandidateDslBuilder> {
            scenario {
                id = "minimum"
                given(LeaseCandidateDslBuilder()) {
                    spec {
                        leaseName = PLACEHOLDER
                        preferredStrategies(PLACEHOLDER)
                    }
                }
                expected = LeaseCandidate(
                    spec = LeaseCandidateSpec(
                        leaseName = PLACEHOLDER,
                        preferredStrategies = listOf(PLACEHOLDER)
                    )
                )
            }

            scenario {
                id = "full"
                given(LeaseCandidateDslBuilder()) {
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
                    spec = LeaseCandidateSpec(
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

        private val FAILURE_POSSIBILITIES = possibilities<LeaseCandidate, LeaseCandidateDslBuilder> {
            requireScenario("spec") {
                given(LeaseCandidateDslBuilder())
            }
        }
    }
}