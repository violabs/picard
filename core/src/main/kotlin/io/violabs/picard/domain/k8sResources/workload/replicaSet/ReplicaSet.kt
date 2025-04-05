package io.violabs.picard.domain.k8sResources.workload.replicaSet

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import java.time.LocalDateTime

data class ReplicaSet(
    override val apiVersion: Version = Version.APPS_V1,
    val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ReplicaSet.Version> {
    override val kind: Kind = Kind.REPLICA_SET

    data class Spec(
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val minReadySeconds: Int? = null
    )

    data class Status(
        val replicas: Int,
        val availableReplicas: Int? = null,
        val readyReplicas: Int? = null,
        val fullyLabeledReplicas: Int? = null,
        val conditions: List<Condition>? = null,
        val observedGeneration: Long? = null
    )

    data class Condition(
        val status: BooleanType,
        val type: String,
        val lastTransitionTime: LocalDateTime? = null,
        val message: String? = null,
        val reason: String? = null
    )

    enum class Version(override val ref: String? = null) : APIVersion {
        APPS_V1("apps/v1");

        override fun toString() = refString()
    }
}