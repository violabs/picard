package io.violabs.picard.domain.builder

import io.violabs.picard.domain.PodCondition
import java.time.LocalDateTime

class PodConditionBuilder : Builder<PodCondition> {
    var type: String? = null
    var status: String? = null
    var lastTransitionTime: LocalDateTime? = null
    var lastProbeTime: LocalDateTime? = null

    override fun build(): PodCondition {
        return PodCondition(
            type = requireNotNull(type) { "type cannot be null" },
            status = requireNotNull(status) { "status cannot be null" },
            lastTransitionTime = requireNotNull(lastTransitionTime) { "lastTransitionTime cannot be null" },
            lastProbeTime = lastProbeTime
        )
    }
}