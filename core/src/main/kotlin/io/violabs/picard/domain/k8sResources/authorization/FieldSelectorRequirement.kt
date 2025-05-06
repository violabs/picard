package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class FieldSelectorRequirement(
    val key: String,
    val requirements: String,
    val values: List<String>? = null
) {
    class Builder : DSLBuilder<FieldSelectorRequirement> {
        var key: String? = null
        var requirements: String? = null
        private var values: List<String>? = null

        fun values(vararg values: String) {
            this.values = values.toList()
        }

        override fun build(): FieldSelectorRequirement {
            return FieldSelectorRequirement(
                key = vRequireNotNull(this::key),
                requirements = vRequireNotNull(this::requirements),
                values = values
            )
        }
    }

    class Group : BuilderGroup<FieldSelectorRequirement, Builder>(Builder()) {
        fun requirements(): List<FieldSelectorRequirement>? = items()

        fun addRequirement(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}
