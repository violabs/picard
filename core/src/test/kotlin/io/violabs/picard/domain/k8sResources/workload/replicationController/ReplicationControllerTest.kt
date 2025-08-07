package io.violabs.picard.domain.k8sResources.workload.replicationController


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicationControllerTest : SuccessBuildSim<ReplicationController, ReplicationControllerDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicationControllerTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ReplicationController, ReplicationControllerDslBuilder> {
            scenario {
                id = "minimum"
                given(ReplicationControllerDslBuilder())
                expected = ReplicationController()
            }

            scenario {
                id = "full"
                given(ReplicationControllerDslBuilder()) {
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
                    spec = ReplicationControllerSpec(
                        selector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        template = PodTemplateSpec(),
                        replicas = 1,
                        minReadySeconds = 1
                    ),
                    status = ReplicationControllerStatus(
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