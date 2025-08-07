package io.violabs.picard.domain.k8sResources.policy.limitRange


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeListTest : FullBuildSim<LimitRangeList, LimitRangeListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LimitRangeList, LimitRangeListDslBuilder> {
            scenario {
                id = "minimum"
                given(LimitRangeListDslBuilder()) {
                    items {
                        limitRangeItem {  }
                    }
                }
                expected = LimitRangeList(
                    items = listOf(LimitRange())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LimitRangeList, LimitRangeListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(LimitRangeListDslBuilder())
            }
        }
    }
}