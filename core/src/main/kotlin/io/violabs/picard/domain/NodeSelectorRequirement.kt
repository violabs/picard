package io.violabs.picard.domain

import io.violabs.picard.common.vRequireNotNull

data class NodeSelectorRequirement(
    val key: String,
    val operator: String,
    val values: List<String>? = null
) {
    class Builder : DslBuilder<NodeSelectorRequirement> {
        var key: String? = null
        var operator: String? = null
        private var _values: List<String>? = null

        fun values(vararg values: String) {
            _values = values.toList()
        }

        override fun build(): NodeSelectorRequirement {
            return NodeSelectorRequirement(
                key = vRequireNotNull(this::key),
                operator = vRequireNotNull(this::operator),
                values = _values
            )
        }
    }
}