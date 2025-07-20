package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.common.DslBuilder

data class Strategy(
    override val type: BaseStrategy.Type? = null,
    override val rollingUpdate: BaseStrategy.RollingUpdate? = null
) : BaseStrategy {
    class Builder : DslBuilder<Strategy> {
        var type: BaseStrategy.Type? = null
        private var rollingUpdate: BaseStrategy.RollingUpdate? = null

        fun rollingUpdate(block: BaseStrategy.RollingUpdate.Builder.() -> Unit) {
            rollingUpdate = BaseStrategy.RollingUpdate.Builder().apply(block).build()
        }

        override fun build(): Strategy {
            return Strategy(
                type = type,
                rollingUpdate = rollingUpdate
            )
        }
    }
}