package io.violabs.picard.domain.k8sResources.workload.replicationController


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicationControllerTest : SuccessBuildSim<ReplicationController, ReplicationController.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicationControllerTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ReplicationController, ReplicationController.Builder> {
            scenario {
                id = "minimum"
                given(ReplicationController.Builder())
                expected = ReplicationController()
            }

            scenario {
                id = "full"
                given(ReplicationController.Builder()) {
                    sharedMetadata()
                    spec {
                        selector(PLACEHOLDER to PLACEHOLDER)
                        template { }
                        replicas = 1
                        minReadySeconds = 1
                    }
                    status {
                        replicas = 1
                        readyReplicas = 1
                        availableReplicas = 1
                        fullyLabeledReplicas = 1
                        conditions {
                            sharedCondition()
                        }
                        observedGeneration = 1
                    }
                }
                expected = ReplicationController(
                    metadata = METADATA,
                    spec = ReplicationController.Spec(
                        selector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        template = PodTemplate.Spec(),
                        replicas = 1,
                        minReadySeconds = 1
                    ),
                    status = ReplicationController.Status(
                        replicas = 1,
                        readyReplicas = 1,
                        availableReplicas = 1,
                        fullyLabeledReplicas = 1,
                        conditions = listOf(CONDITION),
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}