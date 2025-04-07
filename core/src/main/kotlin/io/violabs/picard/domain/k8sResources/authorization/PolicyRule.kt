package io.violabs.picard.domain.k8sResources.authorization

data class PolicyRule(
    val verbs: List<String>,
    val apiGroups: List<String>? = null,
    val resources: List<String>? = null,
    val resourceNames: List<String>? = null,
    val nonResourceURLs: List<String>? = null
)
