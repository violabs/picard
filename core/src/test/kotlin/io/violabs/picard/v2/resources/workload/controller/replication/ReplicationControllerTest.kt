package io.violabs.picard.v2.resources.workload.controller.replication

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.ObjectMeta
import org.junit.jupiter.api.BeforeAll

class ReplicationControllerTest : SuccessBuildSim<ReplicationController, ReplicationControllerV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicationControllerTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ReplicationController, ReplicationControllerV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ReplicationControllerV2DslBuilder())
                expected = ReplicationController()
            }

            scenario {
                id = "full"
                given(ReplicationControllerV2DslBuilder()) {
                    metadata {
                        name = PLACEHOLDER
                        namespace = PLACEHOLDER
                    }
                    spec {
                        selector(PLACEHOLDER to PLACEHOLDER)
                        template = PodTemplate.Spec()
                        replicas = 1
                        minReadySeconds = 1
                    }
                    status {
                        replicas = 1
                        availableReplicas = 1
                        readyReplicas = 1
                        fullyLabeledReplicas = 1
                        conditions {
                            replicationControllerCondition {
                                status = PLACEHOLDER
                                type = PLACEHOLDER
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
                        }
                        observedGeneration = 1
                    }
                }
                expected = ReplicationController(
                    metadata = ObjectMeta(
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER
                    ),
                    spec = ReplicationControllerSpec(
                        selector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        template = PodTemplate.Spec(),
                        replicas = 1,
                        minReadySeconds = 1
                    ),
                    status = ReplicationControllerStatus(
                        replicas = 1,
                        availableReplicas = 1,
                        readyReplicas = 1,
                        fullyLabeledReplicas = 1,
                        conditions = listOf(
                            ReplicationControllerCondition(
                                status = PLACEHOLDER,
                                type = PLACEHOLDER,
                                lastTransitionTime = NOW,
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