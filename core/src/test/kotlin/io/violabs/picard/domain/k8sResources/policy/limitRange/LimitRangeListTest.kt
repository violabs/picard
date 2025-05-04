package io.violabs.picard.domain.k8sResources.policy.limitRange


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeListTest : FullBuildSim<LimitRangeList, LimitRangeList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LimitRangeList, LimitRangeList.Builder> {
            scenario {
                id = "minimum"
                given(LimitRangeList.Builder()) {
                    items {
                        limitRangeItem {  }
                    }
                }
                expected = LimitRangeList(
                    items = listOf(LimitRange())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LimitRangeList, LimitRangeList.Builder> {
            requireNotEmptyScenario("items") {
                given(LimitRangeList.Builder())
            }
        }
    }
}