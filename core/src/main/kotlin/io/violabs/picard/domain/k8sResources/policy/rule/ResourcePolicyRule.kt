package io.violabs.picard.domain.k8sResources.policy.rule

data class ResourcePolicyRule(
    val apiGroups: List<String>,
    val resources: List<String>,
    val verbs: List<String>,
    val clusterScope: Boolean? = null,
    val namespaces: List<String>? = null
)