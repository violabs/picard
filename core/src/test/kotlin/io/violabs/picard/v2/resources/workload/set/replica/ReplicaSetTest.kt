package io.violabs.picard.v2.resources.workload.set.replica

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.ObjectMeta
import org.junit.jupiter.api.BeforeAll

class ReplicaSetTest : SuccessBuildSim<ReplicaSetV2, ReplicaSetV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicaSetTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ReplicaSetV2, ReplicaSetV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ReplicaSetV2DslBuilder()) {
                    spec {
                        selector {
                            sharedSelector()
                        }
                    }
                }
                expected = ReplicaSetV2(
                    spec = ReplicaSetSpec(
                        selector = Common.LABEL_SELECTOR
                    )
                )
            }

            scenario {
                id = "full"
                given(ReplicaSetV2DslBuilder()) {
                    metadata {
                        name = PLACEHOLDER
                        namespace = PLACEHOLDER
                    }
                    spec {
                        selector {
                            sharedSelector()
                        }
                        template = PodTemplate.Spec()
                        replicas = 1
                        minReadySeconds = 1
                    }
                    status {
                        replicas = 1
                        availableReplicas = 1
                        readyReplicas = 1
                        terminatingReplicas = 1
                        fullyLabeledReplicas = 1
                        conditions {
                            replicaSetCondition {
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
                expected = ReplicaSetV2(
                    metadata = ObjectMeta(
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER
                    ),
                    spec = ReplicaSetSpec(
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplate.Spec(),
                        replicas = 1,
                        minReadySeconds = 1
                    ),
                    status = ReplicaSetStatus(
                        replicas = 1,
                        availableReplicas = 1,
                        readyReplicas = 1,
                        terminatingReplicas = 1,
                        fullyLabeledReplicas = 1,
                        conditions = listOf(
                            ReplicaSetCondition(
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