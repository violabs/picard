package io.violabs.picard.domain.k8sResources.workload.daemonSet

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.BaseStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonSetTest : SuccessBuildSim<DaemonSet, DaemonSetDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonSetTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<DaemonSet, DaemonSetDslBuilder> {
            scenario {
                id = "minimum"
                given(DaemonSetDslBuilder())
                expected = DaemonSet()
            }

            scenario {
                id = "full - without template spec"
                given(DaemonSetDslBuilder()) {
                    sharedMetadata()
                    spec {
                        selector {
                            sharedSelector()
                        }
                        template {

                        }

                        minReadySeconds = 1
                        updateStrategy {
                            type = BaseStrategy.Type.RollingUpdate
                            rollingUpdate {
                                maxUnavailable(1)
                                maxSurge(1)
                            }
                        }
                        revisionHistoryLimit = 1
                    }
                    this.status {
                        numberReady = 1
                        numberAvailable = 1
                        numberUnavailable = 1
                        numberMisscheduled = 1
                        desiredNumberScheduled = 1
                        currentNumberScheduled = 1
                        updatedNumberScheduled = 1
                        collisionCount = 1
                        conditions {
                            sharedCondition()
                        }
                        observedGeneration = 1
                    }
                }
                expected = DaemonSet(
                    metadata = METADATA,
                    spec = DaemonSetSpec(
                        selector = LABEL_SELECTOR,
                        template = PodTemplateSpec(),
                        minReadySeconds = 1,
                        updateStrategy = UPDATE_STRATEGY,
                        revisionHistoryLimit = 1
                    ),
                    status = DaemonSetStatus(
                        numberReady = 1,
                        numberAvailable = 1,
                        numberUnavailable = 1,
                        numberMisscheduled = 1,
                        desiredNumberScheduled = 1,
                        currentNumberScheduled = 1,
                        updatedNumberScheduled = 1,
                        collisionCount = 1,
                        conditions = listOf(CONDITION),
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}