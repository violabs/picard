package io.violabs.picard.domain.k8sResources.workload.replicaSet


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicaSetTest : SuccessBuildSim<ReplicaSet, ReplicaSetDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicaSetTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ReplicaSet, ReplicaSetDslBuilder> {
            scenario {
                id = "minimum"
                given(ReplicaSetDslBuilder())
                expected = ReplicaSet()
            }

            scenario {
                id = "full"
                given(ReplicaSetDslBuilder()) {
                    sharedMetadata()
                    spec {
                        selector {
                            sharedSelector()
                        }
                        template {

                        }
                        replicas = 1
                        minReadySeconds = 1
                    }
                    status {
                        replicas = 1
                        fullyLabeledReplicas = 1
                        readyReplicas = 1
                        availableReplicas = 1
                        conditions {
                            sharedCondition()
                        }
                        observedGeneration = 1
                    }
                }
                expected = ReplicaSet(
                    metadata = METADATA,
                    spec = ReplicaSetSpec(
                        selector = LABEL_SELECTOR,
                        template = PodTemplateSpec(),
                        replicas = 1,
                        minReadySeconds = 1
                    ),
                    status = ReplicaSetStatus(
                        replicas = 1,
                        fullyLabeledReplicas = 1,
                        readyReplicas = 1,
                        availableReplicas = 1,
                        conditions = listOf(CONDITION),
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}