package io.violabs.picard.v2.resources.policy.limit


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeTest : SuccessBuildSim<LimitRange, LimitRangeV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LimitRange, LimitRangeV2DslBuilder> {
            scenario {
                id = "minimum"
                given(LimitRangeV2DslBuilder())
                expected = LimitRange()
            }

            scenario {
                id = "full"
                given(LimitRangeV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        limits {
                            limitRangeItem {
                                type = PLACEHOLDER
                                default(QUANTITY_PAIR)
                                defaultRequest(QUANTITY_PAIR)
                                max(QUANTITY_PAIR)
                                maxLimitRequestRatio(QUANTITY_PAIR)
                                min(QUANTITY_PAIR)
                            }
                        }
                    }
                }
                expected = LimitRange(
                    metadata = Common.OBJECT_META,
                    spec = LimitRangeSpec(
                        limits = listOf(
                            LimitRangeItem(
                                type = PLACEHOLDER,
                                default = QUANTITY_MAP,
                                defaultRequest = QUANTITY_MAP,
                                max = QUANTITY_MAP,
                                maxLimitRequestRatio = QUANTITY_MAP,
                                min = QUANTITY_MAP
                            )
                        )
                    )
                )
            }
        }
    }
}