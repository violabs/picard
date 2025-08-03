package io.violabs.picard.v2.resources.workload.set.stateful

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimV2
import org.junit.jupiter.api.BeforeAll

class StatefulSetTest : SuccessBuildSim<StatefulSetV2, StatefulSetV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StatefulSetV2, StatefulSetV2DslBuilder> {
            scenario {
                id = "minimum"
                given(StatefulSetV2DslBuilder()) {
                    spec {
                        serviceName = PLACEHOLDER
                        selector {
                            sharedSelector()
                        }
                        template = PodTemplate.Spec()
                    }
                }
                expected = StatefulSetV2(
                    spec = StatefulSetSpec(
                        serviceName = PLACEHOLDER,
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplate.Spec()
                    )
                )
            }

            scenario {
                id = "full"
                given(StatefulSetV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        serviceName = PLACEHOLDER
                        selector {
                            sharedSelector()
                        }
                        template = PodTemplate.Spec()
                        replicas = 1
                        updateStrategy {
                            type = PLACEHOLDER
                            rollingUpdate {
                                maxUnavailable = IntOrString(1)
                                partition = 1
                            }
                        }
                        podManagementPolicy = PLACEHOLDER
                        revisionHistoryLimit = 1
                        minReadySeconds = 1
                        volumeClaimTemplates {
                            persistentVolumeClaimV2 {

                            }
                        }
                        persistentVolumeClaimRetentionPolicy {
                            whenDeleted = PLACEHOLDER
                            whenScaled = PLACEHOLDER
                        }
                        ordinals {
                            start = 1
                        }
                    }
                    status {
                        replicas = 1
                        readyReplicas = 1
                        currentReplicas = 1
                        updatedReplicas = 1
                        availableReplicas = 1
                        collisionCount = 1
                        currentRevision = PLACEHOLDER
                        updateRevision = PLACEHOLDER
                        observedGeneration = 1
                    }
                }
                expected = StatefulSetV2(
                    metadata = Common.OBJECT_META,
                    spec = StatefulSetSpec(
                        serviceName = PLACEHOLDER,
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplate.Spec(),
                        replicas = 1,
                        updateStrategy = StatefulSetUpdateStrategy(
                            type = PLACEHOLDER,
                            rollingUpdate = RollingUpdateStatefulSetStrategy(
                                maxUnavailable = IntOrString(1),
                                partition = 1
                            )
                        ),
                        podManagementPolicy = PLACEHOLDER,
                        revisionHistoryLimit = 1,
                        minReadySeconds = 1,
                        volumeClaimTemplates = listOf(
                            PersistentVolumeClaimV2()
                        ),
                        persistentVolumeClaimRetentionPolicy = StatefulSetPersistentVolumeClaimRetentionPolicy(
                            whenDeleted = PLACEHOLDER,
                            whenScaled = PLACEHOLDER
                        ),
                        ordinals = StatefulSetOrdinals(1)
                    ),
                    status = StatefulSetStatus(
                        replicas = 1,
                        readyReplicas = 1,
                        currentReplicas = 1,
                        updatedReplicas = 1,
                        availableReplicas = 1,
                        collisionCount = 1,
                        currentRevision = PLACEHOLDER,
                        updateRevision = PLACEHOLDER,
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}