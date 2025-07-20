package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BaseK8s
import io.violabs.picard.common.DslBuilder

interface BaseStrategy : BaseK8s {
    val type: Type?
    val rollingUpdate: RollingUpdate?

    enum class Type {
        Recreate,
        RollingUpdate
    }

    data class RollingUpdate(
        val maxSurge: Int? = null,
        val maxUnavailable: Int? = null
    ) {
        class Builder : DslBuilder<RollingUpdate> {
            private var maxSurge: Int? = null
            private var maxUnavailable: Int? = null

            fun maxSurge(maxSurge: Int) {
                this.maxSurge = maxSurge
            }

            fun maxSurge(maxSurge: String) {
                this.maxSurge = maxSurge.toInt()
            }

            fun maxUnavailable(maxUnavailable: Int) {
                this.maxUnavailable = maxUnavailable
            }

            fun maxUnavailable(maxUnavailable: String) {
                this.maxUnavailable = maxUnavailable.toInt()
            }

            override fun build(): RollingUpdate {
                return RollingUpdate(
                    maxSurge = maxSurge,
                    maxUnavailable = maxUnavailable
                )
            }
        }
    }
}