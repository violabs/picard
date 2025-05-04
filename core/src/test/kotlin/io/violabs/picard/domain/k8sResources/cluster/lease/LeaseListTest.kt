package io.violabs.picard.domain.k8sResources.cluster.lease


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseListTest : FullBuildSim<LeaseList, LeaseList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseList, LeaseList.Builder> {
            scenario {
                id = "minimum"
                given(LeaseList.Builder()) {
                    items {
                        leaseItem {}
                    }
                }
                expected = LeaseList(
                    items = listOf(Lease())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LeaseList, LeaseList.Builder> {
            requireNotEmptyScenario("items") {
                given(LeaseList.Builder())
            }
        }
    }
}