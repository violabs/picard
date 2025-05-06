package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.Operator

data class ScopedResourceSelectorRequirement(
    val operator: Operator,
    val scopeName: String,
    val values: List<String>? = null
) {
    class Builder : DSLBuilder<ScopedResourceSelectorRequirement> {
        var operator: Operator? = null
        var scopeName: String? = null
        private var values: List<String>? = null

        fun values(vararg values: String) {
            this.values = values.toList()
        }

        override fun build(): ScopedResourceSelectorRequirement {
            return ScopedResourceSelectorRequirement(
                operator = vRequireNotNull(this::operator),
                scopeName = vRequireNotNull(this::scopeName),
                values = values
            )
        }
    }

    class Group : BuilderGroup<ScopedResourceSelectorRequirement, Builder>(Builder()) {
        fun requirements(): List<ScopedResourceSelectorRequirement>? = items()

        fun addScopedResourceSelectorRequirement(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}