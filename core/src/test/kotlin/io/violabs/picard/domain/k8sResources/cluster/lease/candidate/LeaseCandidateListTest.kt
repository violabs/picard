package io.violabs.picard.domain.k8sResources.cluster.lease.candidate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateListTest : FullBuildSim<LeaseCandidateList, LeaseCandidateListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseCandidateList, LeaseCandidateListDslBuilder> {
            scenario {
                id = "minimum"
                given(LeaseCandidateListDslBuilder()) {
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
                            spec = LeaseCandidateSpec(
                                leaseName = PLACEHOLDER,
                                preferredStrategies = listOf(PLACEHOLDER)
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LeaseCandidateList, LeaseCandidateListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(LeaseCandidateListDslBuilder())
            }
        }
    }
}