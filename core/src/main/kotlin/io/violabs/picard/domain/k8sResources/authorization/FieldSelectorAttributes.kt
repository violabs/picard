package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.common.DSLBuilder

data class FieldSelectorAttributes(
    val rawSelector: String? = null,
    val resourceAttributes: List<FieldSelectorRequirement>? = null
) {
    class Builder : DSLBuilder<FieldSelectorAttributes> {
        var rawSelector: String? = null
        private var resourceAttributes: List<FieldSelectorRequirement>? = null

        fun resourceAttributes(scope: FieldSelectorRequirement.Group.() -> Unit) {
            resourceAttributes = FieldSelectorRequirement.Group().apply(scope).requirements()
        }

        override fun build(): FieldSelectorAttributes {
            return FieldSelectorAttributes(rawSelector, resourceAttributes)
        }
    }
}
