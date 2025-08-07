package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.policy.LimitResponse
import io.violabs.picard.domain.k8sResources.policy.QueuingConfiguration
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
                        sharedMetadata()
                        spec {
                            type = PriorityLevelConfiguration.Type.Exempt
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
                        this.status {
                            conditions {
                                sharedCondition()
                            }
                        }
                    }
                    expected = PriorityLevelConfiguration(
                        metadata = METADATA,
                        spec = PriorityLevelConfigurationSpec(
                            type = PriorityLevelConfiguration.Type.Exempt,
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