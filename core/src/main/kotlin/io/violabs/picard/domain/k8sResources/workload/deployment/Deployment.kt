package io.violabs.picard.domain.k8sResources.workload.deployment

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import java.time.LocalDateTime

data class Deployment(
    override val apiVersion: Version = Version.APPS_V1,
    val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Deployment.Version> {
    override val kind: Kind = Kind.DEPLOYMENT

    data class Spec(
        val selector: LabelSelector,
        val template: PodTemplate.Spec? = null,
        val replicas: Int? = null,
        val minReadySeconds: Int? = null,
        val strategy: Strategy? = null,
        val revisionHistoryLimit: Int? = null,
        val progressDeadlineSeconds: Int? = null,
        val paused: Boolean? = null
    )

    data class Strategy(
        val type: Type? = null,
        val rollingUpdate: RollingUpdate? = null
    ) {
        enum class Type : K8sEnum {
            RECREATE,
            ROLLING_UPDATE;

            override fun toString(): String = properCase()
        }

        class RollingUpdate private constructor(
            private val maxSurgeNum: Int? = null,
            private val maxSurgeString: String? = null,
            private val maxUnavailableNum: Int? = null,
            private val maxUnavailableString: String? = null,
        ) {
            constructor(maxSurge: Int? = null, maxUnavailable: Int? = null) : this(
                maxSurgeNum = maxSurge,
                maxUnavailableNum = maxUnavailable,
            )

            constructor(maxSurge: Int? = null, maxUnavailable: String? = null) : this(
                maxSurgeNum = maxSurge,
                maxUnavailableString = maxUnavailable,
            )

            constructor(maxSurge: String? = null, maxUnavailable: Int? = null) : this(
                maxSurgeString = maxSurge,
                maxUnavailableNum = maxUnavailable,
            )

            constructor(maxSurge: String? = null, maxUnavailable: String? = null) : this(
                maxSurgeString = maxSurge,
                maxUnavailableString = maxUnavailable,
            )

            val maxSurge: Int =
                maxSurgeNum
                    ?: maxSurgeString?.toInt()
                    ?: throw IllegalArgumentException("maxSurge can't be null")

            val maxUnavailable: Int =
                maxUnavailableNum
                    ?: maxUnavailableString?.toInt()
                    ?: throw IllegalArgumentException("maxUnavailable can't be null")

            override fun toString(): String = "Deployment{maxSurgeNum=$maxSurge, maxUnavailable=$maxUnavailable}"
        }
    }

    data class Status(
        val replicas: Int,
        val availableReplicas: Int? = null,
        val readyReplicas: Int? = null,
        val unavailableReplicas: Int? = null,
        val updatedReplicas: Int? = null,
        val collisionCount: Int? = null,
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