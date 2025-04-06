//package io.violabs.picard.domain.builder
//
//import io.violabs.picard.domain.k8sResources.workload.BaseStrategy
//import io.violabs.picard.domain.k8sResources.workload.Strategy
//
//class StrategyBuilder : Builder<Strategy> {
//    var type: BaseStrategy.Type? = null
//    private var rollingUpdate: BaseStrategy.RollingUpdate? = null
//
//    override fun build(): Strategy {
//        return Strategy(
//            type = requireNotNull(type) { "Please provide a type" },
//            rollingUpdate = rollingUpdate
//        )
//    }
//
//    fun rollingUpdate(scope: RollingUpdateBuilder.() -> Unit) {
//        rollingUpdate = scopedBuild(::RollingUpdateBuilder, scope)
//    }
//}