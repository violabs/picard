package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class CustomResourceColumnDefinition(
    val jsonPath: String,
    val name: String,
    val type: String,
    val description: String? = null,
    val format: String? = null,
    val priority: Int? = null
) {
    class Builder : DSLBuilder<CustomResourceColumnDefinition> {
        var jsonPath: String? = null
        var name: String? = null
        var type: String? = null
        var description: String? = null
        var format: String? = null
        var priority: Int? = null

        override fun build(): CustomResourceColumnDefinition {
            return CustomResourceColumnDefinition(
                jsonPath = vRequireNotNull(this::jsonPath),
                name = vRequireNotNull(this::name),
                type = vRequireNotNull(this::type),
                description = description,
                format = format,
                priority = priority
            )
        }
    }

    class Group : BuilderGroup<CustomResourceColumnDefinition, Builder>(Builder()) {
        fun definitions(): List<CustomResourceColumnDefinition>? = items()

        fun definition(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}