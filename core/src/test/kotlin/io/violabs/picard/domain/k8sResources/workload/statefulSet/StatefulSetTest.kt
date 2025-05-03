package io.violabs.picard.domain.k8sResources.workload.statefulSet


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaim
import io.violabs.picard.domain.k8sResources.workload.BaseStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetTest : SuccessBuildSim<StatefulSet, StatefulSet.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val PERSISTENT_VOLUME_CLAIM_RETENTION_POLICY = PersistentVolumeClaimRetentionPolicy(
            whenDeleted = PLACEHOLDER,
            whenScaled = PLACEHOLDER
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StatefulSet, StatefulSet.Builder> {
            scenario {
                id = "minimum"
                given(StatefulSet.Builder())
                expected = StatefulSet()
            }

            scenario {
                id = "full"
                given(StatefulSet.Builder()) {
                    sharedMetadata()
                    spec {
                        serviceName = PLACEHOLDER
                        selector {
                            sharedSelector()
                        }
                        template {}
                        replicas = 1
                        updateStrategy {
                            type = BaseStrategy.Type.RollingUpdate
                            rollingUpdate {
                                maxUnavailable(1)
                                maxSurge(1)
                            }
                        }
                        podManagementPolicy = PLACEHOLDER
                        revisionHistoryLimit = 1
                        volumeClaimTemplates {
                            claim { }
                        }
                        minReadySeconds = 1
                        persistentVolumeClaimRetentionPolicy {
                            whenDeleted = PLACEHOLDER
                            whenScaled = PLACEHOLDER
                        }
                        ordinals(1)
                    }
                    status {
                        replicas = 1
                        readyReplicas = 1
                        currentReplicas = 1
                        updatedReplicas = 1
                        availableReplicas = 1
                        collisionCount = 1
                        conditions {
                            sharedCondition()
                        }
                        currentRevision = PLACEHOLDER
                        updateRevision = PLACEHOLDER
                        observedGeneration = 1
                    }
                }
                expected = StatefulSet(
                    metadata = METADATA,
                    spec = StatefulSet.Spec(
                        serviceName = PLACEHOLDER,
                        selector = LABEL_SELECTOR,
                        template = PodTemplate.Spec(),
                        replicas = 1,
                        updateStrategy = UPDATE_STRATEGY,
                        podManagementPolicy = PLACEHOLDER,
                        revisionHistoryLimit = 1,
                        volumeClaimTemplates = listOf(PersistentVolumeClaim()),
                        minReadySeconds = 1,
                        persistentVolumeClaimRetentionPolicy = PERSISTENT_VOLUME_CLAIM_RETENTION_POLICY,
                        ordinals = Ordinals(1)
                    ),
                    status = StatefulSet.Status(
                        replicas = 1,
                        readyReplicas = 1,
                        currentReplicas = 1,
                        updatedReplicas = 1,
                        availableReplicas = 1,
                        collisionCount = 1,
                        conditions = listOf(CONDITION),
                        currentRevision = PLACEHOLDER,
                        updateRevision = PLACEHOLDER,
                        observedGeneration = 1
                    )
                )
            }
        }
    }
}