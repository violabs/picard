package io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.condition.ServiceCondition
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodDisruptionBudgetTest : SuccessBuildSim<PodDisruptionBudget, PodDisruptionBudgetDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodDisruptionBudgetTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodDisruptionBudget, PodDisruptionBudgetDslBuilder> {
            scenario {
                id = "minimum"
                given(PodDisruptionBudgetDslBuilder())
                expected = PodDisruptionBudget()
            }

            scenario {
                id = "minimum"
                given(PodDisruptionBudgetDslBuilder()) {
                    sharedMetadata()
                    spec {
                        maxUnavailable(1)
                        minAvailable(1)
                        selector {
                            sharedSelector()
                        }
                        unhealthyPodEvictionPolicy = PLACEHOLDER
                    }
                    this.status {
                        currentHealthy = 1
                        desiredHealthy = 1
                        disruptionsAllowed = 1
                        expectedPods = 1
                        conditions {
                            condition {
                                status = BooleanType.True
                                type = PLACEHOLDER
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                                observedGeneration = 1L
                            }
                        }
                        disruptedPods(PLACEHOLDER to NOW)
                        observedGeneration = 1L
                    }
                }
                expected = PodDisruptionBudget(
                    metadata = METADATA,
                    spec = PodDisruptionBudgetSpec(
                        maxUnavailable = INT_OR_STRING_1,
                        minAvailable = INT_OR_STRING_1,
                        selector = LABEL_SELECTOR,
                        unhealthyPodEvictionPolicy = PLACEHOLDER
                    ),
                    status = PodDisruptionBudgetStatus(
                        currentHealthy = 1,
                        desiredHealthy = 1,
                        disruptionsAllowed = 1,
                        expectedPods = 1,
                        conditions = listOf(
                            ServiceCondition(
                                status = BooleanType.True,
                                type = PLACEHOLDER,
                                lastTransitionTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER,
                                observedGeneration = 1L
                            )
                        ),
                        disruptedPods = mapOf(PLACEHOLDER to NOW),
                        observedGeneration = 1L
                    )
                )
            }
        }
    }
}