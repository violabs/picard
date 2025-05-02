package io.violabs.picard.domain.k8sResources.workload.daemonSet

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.BaseStrategy
import io.violabs.picard.domain.k8sResources.workload.UpdateStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonSetTest : SuccessBuildSim<DaemonSet, DaemonSet.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonSetTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<DaemonSet, DaemonSet.Builder> {
            scenario {
                id = "minimum"
                given(DaemonSet.Builder())
                expected = DaemonSet()
            }

            scenario {
                id = "full - without template spec"
                given(DaemonSet.Builder()) {
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
                    spec = DaemonSet.Spec(
                        selector = LABEL_SELECTOR,
                        template = PodTemplate.Spec(),
                        minReadySeconds = 1,
                        updateStrategy = UpdateStrategy(
                            type = BaseStrategy.Type.RollingUpdate,
                            rollingUpdate = BaseStrategy.RollingUpdate(
                                maxUnavailable = 1,
                                maxSurge = 1
                            )
                        ),
                        revisionHistoryLimit = 1
                    ),
                    status = DaemonSet.Status(
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