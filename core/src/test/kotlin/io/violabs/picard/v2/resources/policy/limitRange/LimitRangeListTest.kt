package io.violabs.picard.v2.resources.policy.limitRange


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeListTest : FullBuildSim<LimitRangeListV2, LimitRangeListV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LimitRangeListV2, LimitRangeListV2DslBuilder> {
            scenario {
                id = "minimum"
                given(LimitRangeListV2DslBuilder()) {
                    items(LimitRangeV2())
                }
                expected = LimitRangeListV2(
                    items = listOf(LimitRangeV2())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<LimitRangeListV2, LimitRangeListV2DslBuilder> {
            requireNotEmptyScenario("items") {
                given(LimitRangeListV2DslBuilder())
            }
        }
    }
}