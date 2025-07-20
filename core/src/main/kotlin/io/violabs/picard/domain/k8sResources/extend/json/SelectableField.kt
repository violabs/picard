package io.violabs.picard.domain.k8sResources.extend.json

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class SelectableField(
    val jsonPath: String
) {
    class Builder : DslBuilder<SelectableField> {
        var jsonPath: String? = null

        override fun build(): SelectableField {
            return SelectableField(
                jsonPath = vRequireNotNull(this::jsonPath)
            )
        }
    }

    class Group : BuilderGroup<SelectableField, Builder>(Builder()) {
        fun fields(): List<SelectableField>? = items()

        fun addSelectableField(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}