package io.violabs.picard.domain.label

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotNull

data class LabelSelectorRequirement(
    val key: String,
    val operator: String,
    val values: List<String>? = null
) {
    class Builder : DSLBuilder<LabelSelectorRequirement> {
        var key: String? = null
        var operator: String? = null
        private var values: List<String>? = null

        fun values(vararg values: String) {
            this.values = values.toList()
        }

        override fun build(): LabelSelectorRequirement {
            return LabelSelectorRequirement(
                key = vRequireNotNull(this::key),
                operator = vRequireNotNull(this::operator),
                values = values
            )
        }
    }

    class Group : BuilderGroup<LabelSelectorRequirement, Builder>(Builder()) {
        fun requirements(): List<LabelSelectorRequirement>? = items()

        fun addLabelSelectorRequirement(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}