package io.violabs.picard.domain.k8sResources.workload.statefulSet


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaim
import io.violabs.picard.domain.k8sResources.workload.BaseStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetTest : SuccessBuildSim<StatefulSet, StatefulSetDslBuilder>() {
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

        private val SUCCESS_POSSIBILITIES = possibilities<StatefulSet, StatefulSetDslBuilder> {
            scenario {
                id = "minimum"
                given(StatefulSetDslBuilder())
                expected = StatefulSet()
            }

            scenario {
                id = "full"
                given(StatefulSetDslBuilder()) {
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
                            persistentVolumeClaimItem { }
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
                    spec = StatefulSetSpec(
                        serviceName = PLACEHOLDER,
                        selector = LABEL_SELECTOR,
                        template = PodTemplateSpec(),
                        replicas = 1,
                        updateStrategy = UPDATE_STRATEGY,
                        podManagementPolicy = PLACEHOLDER,
                        revisionHistoryLimit = 1,
                        volumeClaimTemplates = listOf(PersistentVolumeClaim()),
                        minReadySeconds = 1,
                        persistentVolumeClaimRetentionPolicy = PERSISTENT_VOLUME_CLAIM_RETENTION_POLICY,
                        ordinals = Ordinals(1)
                    ),
                    status = StatefulSetStatus(
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