package io.violabs.picard.domain.k8sResources.cluster.lease.candidate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateListTest : FullBuildSim<LeaseCandidateList, LeaseCandidateList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseCandidateList, LeaseCandidateList.Builder> {
            scenario {
                id = "minimum"
                given(LeaseCandidateList.Builder()) {
                    items {
                        candidate {
                            spec {
                                leaseName = PLACEHOLDER
                                preferredStrategies(PLACEHOLDER)
                            }
                        }
                    }
                }
                expected = LeaseCandidateList(
                    items = listOf(
                        LeaseCandidate(
                            spec = LeaseCandidate.Spec(
                                leaseName = PLACEHOLDER,
                                preferredStrategies = listOf(PLACEHOLDER)
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LeaseCandidateList, LeaseCandidateList.Builder> {
            requireNotEmptyScenario("items") {
                given(LeaseCandidateList.Builder())
            }
        }
    }
}