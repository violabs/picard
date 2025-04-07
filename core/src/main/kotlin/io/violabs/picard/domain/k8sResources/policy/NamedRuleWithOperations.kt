package io.violabs.picard.domain.k8sResources.policy

data class NamedRuleWithOperations(
    val apiGroups: List<String>? = null,
    val apiVersion: List<String>? = null,
    val operations: List<String>? = null,
    val resourceNames: List<String>? = null,
    val resources: List<String>? = null,
    val scope: String? = null
)