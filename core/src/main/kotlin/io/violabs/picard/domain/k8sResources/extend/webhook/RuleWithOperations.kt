package io.violabs.picard.domain.k8sResources.extend.webhook

data class RuleWithOperations(
    val apiGroups: List<String>? = null,
    val apiVersions: List<String>? = null,
    val operations: List<String>? = null,
    val resources: List<String>? = null,
    val scope: String? = null
)