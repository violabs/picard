package io.violabs.picard.domain.k8sResources.authorization

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
)
