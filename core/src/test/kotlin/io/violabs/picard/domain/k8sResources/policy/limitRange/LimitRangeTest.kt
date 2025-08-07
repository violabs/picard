package io.violabs.picard.domain.k8sResources.policy.limitRange


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeTest : SuccessBuildSim<LimitRange, LimitRangeDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<LimitRange, LimitRangeDslBuilder> {
            scenario {
                id = "minimum"
                given(LimitRangeDslBuilder())
                expected = LimitRange()
            }

            scenario {
                id = "full"
                given(LimitRangeDslBuilder()) {
                    sharedMetadata()
                    spec {
                        limits {
                            addLimitRangeItem {
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
                    metadata = METADATA,
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