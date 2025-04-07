package io.violabs.picard.domain.k8sResources.policy.rule

data class NonResourcePolicyRule(
    val nonResourceURLs: List<String>,
    val verbs: List<String>
)