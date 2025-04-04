package io.violabs.picard.domain.builder

import io.violabs.picard.domain.ReadinessGate

class ReadinessGateBuilder : Builder<ReadinessGate> {
    var conditionType: String? = null

    override fun build(): ReadinessGate {
        return ReadinessGate(
            conditionType = requireNotNull(conditionType) { "conditionType cannot be null" }
        )
    }
}