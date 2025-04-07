package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.LabelSelector

data class MatchResources(
    val excludeResourceRules: List<NamedRuleWithOperations>? = null,
    val matchPolicy: String? = null,
    val namespaceSelector: LabelSelector? = null,
    val objectSelector: LabelSelector? = null,
    val resourceRules: List<NamedRuleWithOperations>? = null
)