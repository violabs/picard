package io.violabs.picard.v2.resources.cluster.lease

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseListTest : FullBuildSim<LeaseList, LeaseListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseList, LeaseListDslBuilder> {
            scenario {
                id = "minimum"
                given(LeaseListDslBuilder()) {
                    items {
                        lease { }
                    }
                }
                expected = LeaseList(
                    items = listOf(Lease())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LeaseList, LeaseListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(LeaseListDslBuilder())
            }
        }
    }
}