package io.violabs.picard.domain.k8sResources.workload.deployment


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.BaseStrategy
import io.violabs.picard.domain.k8sResources.workload.Strategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeploymentTest : SuccessBuildSim<Deployment, Deployment.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeploymentTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<Deployment, Deployment.Builder> {
            scenario {
                id = "minimum"
                given(Deployment.Builder())
                expected = Deployment()
            }

            scenario {
                id = "full"
                given(Deployment.Builder()) {
                    sharedMetadata()
                    spec {
                        selector {
                            sharedSelector()
                        }

                        template {  }

                        replicas = 1
                        minReadySeconds = 1
                        strategy {
                            type = BaseStrategy.Type.Recreate
                            rollingUpdate {
                                maxSurge(1)
                                maxUnavailable(1)
                            }
                        }

                        revisionHistoryLimit = 1
                        progressDeadlineSeconds = 1
                        paused()
                    }

                    this.status {
                        replicas = 1
                        availableReplicas = 1
                        unavailableReplicas = 1
                        readyReplicas = 1
                        updatedReplicas = 1
                        collisionCount = 1
                        conditions {
                            sharedCondition()
                        }

                        observedGeneration = 1
                    }
                }
                expected = Deployment(
                    metadata = METADATA,
                    spec = Deployment.Spec(
                        selector = LABEL_SELECTOR,
                        template = PodTemplate.Spec(),
                        replicas = 1,
                        minReadySeconds = 1,
                        strategy = Strategy(
                            type = BaseStrategy.Type.Recreate,
                            rollingUpdate = BaseStrategy.RollingUpdate(
                                maxSurge = 1,
                                maxUnavailable = 1
                            )
                        ),
                        revisionHistoryLimit = 1,
                        progressDeadlineSeconds = 1,
                        paused = true
                    ),
                    status = Deployment.Status(
                        replicas = 1,
                        availableReplicas = 1,
                        unavailableReplicas = 1,
                        readyReplicas = 1,
                        updatedReplicas = 1,
                        collisionCount = 1,
                        conditions = listOf(CONDITION),
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}