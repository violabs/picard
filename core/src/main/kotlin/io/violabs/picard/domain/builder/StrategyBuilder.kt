package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Strategy

class StrategyBuilder : Builder<Strategy> {
    var type: Strategy.Type? = null
    private var rollingUpdate: Strategy.RollingUpdate? = null

    override fun build(): Strategy {
        return Strategy(
            type = requireNotNull(type) { "Please provide a type" },
            rollingUpdate = rollingUpdate
        )
    }

    fun rollingUpdate(scope: RollingUpdateBuilder.() -> Unit) {
        rollingUpdate = scopedBuild(::RollingUpdateBuilder, scope)
    }
}