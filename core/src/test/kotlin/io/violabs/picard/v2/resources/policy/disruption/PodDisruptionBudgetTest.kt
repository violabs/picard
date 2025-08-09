package io.violabs.picard.v2.resources.policy.disruption


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
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

        private val CONDITION = Condition(
            lastTransitionTime = NOW,
            message = PLACEHOLDER,
            reason = PLACEHOLDER,
            status = PLACEHOLDER,
            type = PLACEHOLDER,
            observedGeneration = 1L
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodDisruptionBudget, PodDisruptionBudgetDslBuilder> {
            scenario {
                id = "minimum"
                given(PodDisruptionBudgetDslBuilder())
                expected = PodDisruptionBudget()
            }

            scenario {
                id = "full"
                given(PodDisruptionBudgetDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        maxUnavailable = IntOrString(1)
                        minAvailable = IntOrString(1)
                        selector {
                            sharedSelector()
                        }
                        unhealthyPodEvictionPolicy = PLACEHOLDER
                    }
                    status {
                        currentHealthy = 1
                        desiredHealthy = 1
                        disruptionsAllowed = 1
                        expectedPods = 1
                        conditions {
                            condition {
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                                status = PLACEHOLDER
                                type = PLACEHOLDER
                                observedGeneration = 1L
                            }
                        }
                        disruptedPods(PLACEHOLDER to NOW)
                        observedGeneration = 1L
                    }
                }
                expected = PodDisruptionBudget(
                    metadata = Common.OBJECT_META,
                    spec = PodDisruptionBudgetSpec(
                        maxUnavailable = IntOrString(1),
                        minAvailable = IntOrString(1),
                        selector = Common.LABEL_SELECTOR,
                        unhealthyPodEvictionPolicy = PLACEHOLDER
                    ),
                    status = PodDisruptionBudgetStatus(
                        currentHealthy = 1,
                        desiredHealthy = 1,
                        disruptionsAllowed = 1,
                        expectedPods = 1,
                        conditions = listOf(CONDITION),
                        disruptedPods = mapOf(PLACEHOLDER to NOW),
                        observedGeneration = 1L
                    )
                )
            }
        }
    }
}