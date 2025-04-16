package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.LabelSelectorRequirement

data class LabelSelectorAttributes(
    val rawSelector: String? = null,
    val requirements: List<LabelSelectorRequirement>? = null
) {
    class Builder : DSLBuilder<LabelSelectorAttributes> {
        var rawSelector: String? = null
        private var requirements: List<LabelSelectorRequirement>? = null

        fun requirements(scope: LabelSelectorRequirement.Group.() -> Unit) {
            requirements = LabelSelectorRequirement.Group().apply(scope).requirements()
        }

        override fun build(): LabelSelectorAttributes {
            return LabelSelectorAttributes(rawSelector, requirements)
        }
    }
}