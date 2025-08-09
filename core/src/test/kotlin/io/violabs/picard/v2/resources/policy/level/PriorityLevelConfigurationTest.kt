package io.violabs.picard.v2.resources.policy.level


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityLevelConfigurationTest :
    SuccessBuildSim<PriorityLevelConfiguration, PriorityLevelConfigurationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityLevelConfigurationTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val CONDITION = PriorityLevelConfigurationCondition(
            lastTransitionTime = NOW,
            message = PLACEHOLDER,
            reason = PLACEHOLDER,
            status = PLACEHOLDER,
            type = PLACEHOLDER
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<PriorityLevelConfiguration, PriorityLevelConfigurationDslBuilder> {
                scenario {
                    id = "minimum"
                    given(PriorityLevelConfigurationDslBuilder())
                    expected = PriorityLevelConfiguration()
                }

                scenario {
                    id = "full"
                    given(PriorityLevelConfigurationDslBuilder()) {
                        metadata {
                            sharedObjectMeta()
                        }
                        spec {
                            type = PriorityLevelType.Exempt
                            exempt {
                                lendablePercent = 1
                                nominalConcurrencyShares = 1
                            }
                            limited {
                                borrowingLimitPercent = 1
                                lendablePercent = 1
                                limitResponse {
                                    type = LimitResponse.Type.Queue
                                    queuing {
                                        handSize = 1
                                        queueLengthLimit = 1
                                        queues = 1
                                    }
                                }
                                nominalConcurrencyShares = 1
                            }
                        }
                        status {
                            conditions {
                                priorityLevelConfigurationCondition {
                                    lastTransitionTime = NOW
                                    message = PLACEHOLDER
                                    reason = PLACEHOLDER
                                    status = PLACEHOLDER
                                    type = PLACEHOLDER
                                }
                            }
                        }
                    }
                    expected = PriorityLevelConfiguration(
                        metadata = Common.OBJECT_META,
                        spec = PriorityLevelConfigurationSpec(
                            type = PriorityLevelType.Exempt,
                            exempt = ExemptPriorityLevelConfiguration(
                                lendablePercent = 1,
                                nominalConcurrencyShares = 1
                            ),
                            limited = LimitedPriorityLevelConfiguration(
                                borrowingLimitPercent = 1,
                                lendablePercent = 1,
                                limitResponse = LimitResponse(
                                    type = LimitResponse.Type.Queue,
                                    queuing = QueuingConfiguration(
                                        handSize = 1,
                                        queueLengthLimit = 1,
                                        queues = 1
                                    )
                                ),
                                nominalConcurrencyShares = 1
                            )
                        ),
                        status = PriorityLevelConfigurationStatus(
                            conditions = listOf(CONDITION)
                        )
                    )
                }
            }
    }
}