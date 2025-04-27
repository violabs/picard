package io.violabs.picard.domain.k8sResources.extend.json

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class SelectableField(
    val jsonPath: String
) {
    class Builder : DSLBuilder<SelectableField> {
        var jsonPath: String? = null

        override fun build(): SelectableField {
            return SelectableField(
                jsonPath = requireNotNull(jsonPath)
            )
        }
    }

    class Group : BuilderGroup<SelectableField, Builder>(Builder()) {
        fun fields(): List<SelectableField>? = items()

        fun field(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}