package io.violabs.picard.v2.resources.workload.set.replica

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateSpec
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
                given(ReplicaSetDslBuilder()) {
                    spec {
                        selector {
                            sharedSelector()
                        }
                    }
                }
                expected = ReplicaSet(
                    spec = ReplicaSetSpec(
                        selector = Common.LABEL_SELECTOR
                    )
                )
            }

            scenario {
                id = "full"
                given(ReplicaSetDslBuilder()) {
                    metadata {
                        name = PLACEHOLDER
                        namespace = PLACEHOLDER
                    }
                    spec {
                        selector {
                            sharedSelector()
                        }
                        template {}
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
                expected = ReplicaSet(
                    metadata = ObjectMeta(
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER
                    ),
                    spec = ReplicaSetSpec(
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplateSpec(),
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