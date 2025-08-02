package io.violabs.picard.domain.k8sResources.workload.statefulSet

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaim
import io.violabs.picard.domain.k8sResources.workload.UpdateStrategy
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.manifest.WorkloadResource

@Deprecated("Use v2", ReplaceWith("StatefulSetV2", "io.violabs.picard.v2.resources.workload.stateful.set.StatefulSetV2"))
data class StatefulSet(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : WorkloadResource<StatefulSet.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val serviceName: String,
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val updateStrategy: UpdateStrategy? = null,
        val podManagementPolicy: String? = null,
        val revisionHistoryLimit: Int? = null,
        val volumeClaimTemplates: List<PersistentVolumeClaim>? = null,
        val minReadySeconds: Int? = null,
        val persistentVolumeClaimRetentionPolicy: PersistentVolumeClaimRetentionPolicy? = null,
        val ordinals: Ordinals? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            var serviceName: String? = null
            private var selector: LabelSelector? = null
            private var template: PodTemplate.Spec? = null
            var replicas: Int? = null
            private var updateStrategy: UpdateStrategy? = null
            var podManagementPolicy: String? = null
            var revisionHistoryLimit: Int? = null
            private var volumeClaimTemplates: List<PersistentVolumeClaim>? = null
            var minReadySeconds: Int? = null
            private var persistentVolumeClaimRetentionPolicy: PersistentVolumeClaimRetentionPolicy? = null
            private var ordinals: Ordinals? = null

            fun selector(block: LabelSelector.Builder.() -> Unit) {
                selector = LabelSelector.Builder().apply(block).build()
            }

            fun template(block: PodTemplate.Spec.Builder.() -> Unit) {
                template = PodTemplate.Spec.Builder().apply(block).build()
            }

            fun updateStrategy(block: UpdateStrategy.Builder.() -> Unit) {
                updateStrategy = UpdateStrategy.Builder().apply(block).build()
            }

            fun volumeClaimTemplates(block: PersistentVolumeClaim.Group.() -> Unit) {
                volumeClaimTemplates = PersistentVolumeClaim.Group().apply(block).claims()
            }

            fun persistentVolumeClaimRetentionPolicy(block: PersistentVolumeClaimRetentionPolicy.Builder.() -> Unit) {
                persistentVolumeClaimRetentionPolicy =
                    PersistentVolumeClaimRetentionPolicy.Builder().apply(block).build()
            }

            fun ordinals(start: Int) {
                ordinals = Ordinals(start)
            }

            override fun build(): Spec {
                return Spec(
                    serviceName = vRequireNotNull(this::serviceName),
                    selector = vRequireNotNull(this::selector),
                    template = template,
                    replicas = replicas,
                    updateStrategy = updateStrategy,
                    podManagementPolicy = podManagementPolicy,
                    revisionHistoryLimit = revisionHistoryLimit,
                    volumeClaimTemplates = volumeClaimTemplates,
                    minReadySeconds = minReadySeconds,
                    persistentVolumeClaimRetentionPolicy = persistentVolumeClaimRetentionPolicy,
                    ordinals = ordinals
                )
            }
        }
    }

    data class Status(
        val replicas: Int,
        val readyReplicas: Int? = null,
        val currentReplicas: Int? = null,
        val updatedReplicas: Int? = null,
        val availableReplicas: Int? = null,
        val collisionCount: Int? = null,
        val conditions: List<Condition>? = null,
        val currentRevision: String? = null,
        val updateRevision: String? = null,
        val observedGeneration: Long? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            var replicas: Int? = null
            var readyReplicas: Int? = null
            var currentReplicas: Int? = null
            var updatedReplicas: Int? = null
            var availableReplicas: Int? = null
            var collisionCount: Int? = null
            private var conditions: List<Condition>? = null
            var currentRevision: String? = null
            var updateRevision: String? = null
            var observedGeneration: Long? = null

            fun conditions(block: io.violabs.picard.domain.condition.StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(block)
            }

            override fun build(): Status {
                return Status(
                    replicas = vRequireNotNull(this::replicas),
                    readyReplicas = readyReplicas,
                    currentReplicas = currentReplicas,
                    updatedReplicas = updatedReplicas,
                    availableReplicas = availableReplicas,
                    collisionCount = collisionCount,
                    conditions = conditions,
                    currentRevision = currentRevision,
                    updateRevision = updateRevision,
                    observedGeneration = observedGeneration
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        StatefulSet,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): StatefulSet {
            return StatefulSet(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<StatefulSet, Builder>(Builder()) {
        fun statefulSetItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}