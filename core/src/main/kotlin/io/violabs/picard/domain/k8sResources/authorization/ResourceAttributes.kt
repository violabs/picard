package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.DSLBuilder

data class ResourceAttributes(
    val fieldSelector: FieldSelectorAttributes? = null,
    val group: String? = null,
    val labelSelector: LabelSelectorAttributes? = null,
    val name: String? = null,
    val namespace: String? = null,
    val resource: String? = null,
    val subresource: String? = null,
    val verb: K8sVerb? = null,
    val version: String? = null
) {
    class Builder : DSLBuilder<ResourceAttributes> {
        private var fieldSelector: FieldSelectorAttributes? = null
        var group: String? = null
        private var labelSelector: LabelSelectorAttributes? = null
        var name: String? = null
        var namespace: String? = null
        var resource: String? = null
        var subresource: String? = null
        var verb: K8sVerb? = null
        var version: String? = null

        fun fieldSelector(scope: FieldSelectorAttributes.Builder.() -> Unit) {
            fieldSelector = FieldSelectorAttributes.Builder().apply(scope).build()
        }

        fun labelSelector(scope: LabelSelectorAttributes.Builder.() -> Unit) {
            labelSelector = LabelSelectorAttributes.Builder().apply(scope).build()
        }

        override fun build(): ResourceAttributes {
            return ResourceAttributes(
                fieldSelector = fieldSelector,
                group = group,
                labelSelector = labelSelector,
                name = name,
                namespace = namespace,
                resource = resource,
                subresource = subresource,
                verb = verb,
                version = version
            )
        }
    }
}
