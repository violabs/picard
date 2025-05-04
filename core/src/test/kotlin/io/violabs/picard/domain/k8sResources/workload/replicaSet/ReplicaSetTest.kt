package io.violabs.picard.domain.k8sResources.workload.replicaSet


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicaSetTest : SuccessBuildSim<ReplicaSet, ReplicaSet.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicaSetTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ReplicaSet, ReplicaSet.Builder> {
            scenario {
                id = "minimum"
                given(ReplicaSet.Builder())
                expected = ReplicaSet()
            }

            scenario {
                id = "full"
                given(ReplicaSet.Builder()) {
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
                    spec = ReplicaSet.Spec(
                        selector = LABEL_SELECTOR,
                        template = PodTemplate.Spec(),
                        replicas = 1,
                        minReadySeconds = 1
                    ),
                    status = ReplicaSet.Status(
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