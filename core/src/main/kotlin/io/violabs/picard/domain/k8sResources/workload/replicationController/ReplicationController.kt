package io.violabs.picard.domain.k8sResources.workload.replicationController

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import java.time.LocalDateTime

data class ReplicationController(
    override val apiVersion: Version = Version.V1,
    val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ReplicationController.Version> {
    override val kind: Kind = Kind.REPLICATION_CONTROLLER

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = name.lowercase()
    }

    data class Spec(
        val selector: Map<String, String>? = null,
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
}