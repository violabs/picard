package io.violabs.picard.v2.resources.workload.deployment

import io.violabs.picard.Common
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.ObjectMeta
import org.junit.jupiter.api.BeforeAll

class DeploymentTest : SuccessBuildSim<Deployment, DeploymentDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeploymentTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Deployment, DeploymentDslBuilder> {
            scenario {
                id = "minimum"
                given(DeploymentDslBuilder()) {
                    spec {
                        selector {
                            sharedSelector()
                        }
                        template = PodTemplateSpec()
                    }
                }
                expected = Deployment(
                    spec = DeploymentSpec(
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplateSpec()
                    )
                )
            }

            scenario {
                id = "full"
                given(DeploymentDslBuilder()) {
                    metadata {
                        name = PLACEHOLDER
                        namespace = PLACEHOLDER
                    }
                    spec {
                        selector {
                            sharedSelector()
                        }
                        template = PodTemplateSpec()
                        replicas = 1
                        minReadySeconds = 1
                        strategy {
                            type = PLACEHOLDER
                            rollingUpdate {
                                maxSurge = IntOrString(1)
                                maxUnavailable = IntOrString(1)
                            }
                        }
                        revisionHistoryLimit = 1
                        progressDeadlineSeconds = 1
                    }
                    status {
                        replicas = 1
                        availableReplicas = 1
                        readyReplicas = 1
                        unavailableReplicas = 1
                        updatedReplicas = 1
                        terminatingReplicas = 1
                        collisionCount = 1
                        conditions {
                            deploymentCondition {
                                status = PLACEHOLDER
                                type = PLACEHOLDER
                                lastTransitionTime = NOW
                                lastUpdateTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
                        }
                        observedGeneration = 1
                    }
                }
                expected = Deployment(
                    metadata = ObjectMeta(
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER
                    ),
                    spec = DeploymentSpec(
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplateSpec(),
                        replicas = 1,
                        minReadySeconds = 1,
                        strategy = DeploymentStrategy(
                            type = PLACEHOLDER,
                            rollingUpdate = RollingUpdateDeployment(
                                maxSurge = IntOrString(1),
                                maxUnavailable = IntOrString(1)
                            )
                        ),
                        revisionHistoryLimit = 1,
                        progressDeadlineSeconds = 1
                    ),
                    status = DeploymentStatus(
                        replicas = 1,
                        availableReplicas = 1,
                        readyReplicas = 1,
                        unavailableReplicas = 1,
                        updatedReplicas = 1,
                        terminatingReplicas = 1,
                        collisionCount = 1,
                        conditions = listOf(
                            DeploymentCondition(
                                status = PLACEHOLDER,
                                type = PLACEHOLDER,
                                lastTransitionTime = NOW,
                                lastUpdateTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER
                            )
                        ),
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}