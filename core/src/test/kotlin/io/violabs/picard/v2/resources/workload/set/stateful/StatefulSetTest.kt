package io.violabs.picard.v2.resources.workload.set.stateful

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaim
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateSpec
import org.junit.jupiter.api.BeforeAll

class StatefulSetTest : SuccessBuildSim<StatefulSet, StatefulSetDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StatefulSet, StatefulSetDslBuilder> {
            scenario {
                id = "minimum"
                given(StatefulSetDslBuilder()) {
                    spec {
                        serviceName = PLACEHOLDER
                        selector {
                            sharedSelector()
                        }
                        template {}
                    }
                }
                expected = StatefulSet(
                    spec = StatefulSetSpec(
                        serviceName = PLACEHOLDER,
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplateSpec()
                    )
                )
            }

            scenario {
                id = "full"
                given(StatefulSetDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        serviceName = PLACEHOLDER
                        selector {
                            sharedSelector()
                        }
                        template {}
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
                            persistentVolumeClaim {

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
                expected = StatefulSet(
                    metadata = Common.OBJECT_META,
                    spec = StatefulSetSpec(
                        serviceName = PLACEHOLDER,
                        selector = Common.LABEL_SELECTOR,
                        template = PodTemplateSpec(),
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
                            PersistentVolumeClaim()
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