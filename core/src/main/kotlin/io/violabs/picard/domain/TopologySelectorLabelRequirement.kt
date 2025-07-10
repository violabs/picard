package io.violabs.picard.domain

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull

data class TopologySelectorLabelRequirement(val key: String, val values: List<String>) {
    class Builder : DslBuilder<TopologySelectorLabelRequirement> {
        var key: String? = null
        private var values: List<String>? = null

        fun values(vararg values: String) {
            this.values = values.toList()
        }

        override fun build(): TopologySelectorLabelRequirement {
            return TopologySelectorLabelRequirement(
                key = vRequireNotNull(this::key),
                values = vRequireNotEmpty(this::values)
            )
        }
    }

    class Group : BuilderGroup<TopologySelectorLabelRequirement, Builder>(Builder()) {
        fun requirements(): List<TopologySelectorLabelRequirement>? = items()

        fun addTopologySelectorLabelRequirement(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}