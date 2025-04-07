package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BaseK8s
import io.violabs.picard.domain.K8sEnum

data class UpdateStrategy(
    override val type: BaseStrategy.Type? = null,
    override val rollingUpdate: BaseStrategy.RollingUpdate? = null
) : BaseStrategy

data class Strategy(
    override val type: BaseStrategy.Type? = null,
    override val rollingUpdate: BaseStrategy.RollingUpdate? = null
) : BaseStrategy

interface BaseStrategy : BaseK8s {
    val type: Type?
    val rollingUpdate: RollingUpdate?

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