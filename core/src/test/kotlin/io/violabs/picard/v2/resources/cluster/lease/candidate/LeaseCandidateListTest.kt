package io.violabs.picard.v2.resources.cluster.lease.candidate


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
                        leaseCandidate {
                            spec {
                                leaseName = PLACEHOLDER
                                binaryVersion = PLACEHOLDER
                                strategy = PLACEHOLDER
                            }
                        }
                    }
                }
                expected = LeaseCandidateList(
                    items = listOf(
                        LeaseCandidate(
                            spec = LeaseCandidateSpec(
                                leaseName = PLACEHOLDER,
                                binaryVersion = PLACEHOLDER,
                                strategy = PLACEHOLDER
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LeaseCandidateList, LeaseCandidateListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(LeaseCandidateListDslBuilder())
            }
        }
    }
}