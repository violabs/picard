package io.violabs.picard.domain.builder

import io.violabs.picard.domain.ContainerStatus
import io.violabs.picard.domain.PodCondition
import io.violabs.picard.domain.PodStatus

class PodStatusBuilder : Builder<PodStatus> {
    private var conditions: List<PodCondition>? = null
    private var containerStatus: ContainerStatus? = null

    override fun build(): PodStatus {
        return PodStatus(
            conditions = requireNotNull(conditions) { "conditions cannot be null" },
            containerStatus = containerStatus
        )
    }

    fun conditions(scope: PodConditionsBuilder.() -> Unit) {
        conditions = scopedBuild(::PodConditionsBuilder, scope)
    }

    fun containerStatus(scope: ContainerStatusBuilder.() -> Unit) {
        containerStatus = scopedBuild(::ContainerStatusBuilder, scope)
    }
}