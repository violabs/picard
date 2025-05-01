package io.violabs.picard.domain.k8sResources.workload

data class Strategy(
    override val type: BaseStrategy.Type? = null,
    override val rollingUpdate: BaseStrategy.RollingUpdate? = null
) : BaseStrategy