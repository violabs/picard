package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.LabelSelector

data class MatchResources(
    val excludeResourceRules: List<NamedRuleWithOperations>? = null,
    val matchPolicy: String? = null,
    val namespaceSelector: LabelSelector? = null,
    val objectSelector: LabelSelector? = null,
    val resourceRules: List<NamedRuleWithOperations>? = null
) {
    class Builder : DSLBuilder<MatchResources> {
        private var excludeResourceRules: List<NamedRuleWithOperations>? = null
        var matchPolicy: String? = null
        private var namespaceSelector: LabelSelector? = null
        private var objectSelector: LabelSelector? = null
        private var resourceRules: List<NamedRuleWithOperations>? = null

        fun excludeResourceRules(scope: NamedRuleWithOperations.Group.() -> Unit) {
            excludeResourceRules = NamedRuleWithOperations.Group().apply(scope).rules()
        }

        fun namespaceSelector(scope: LabelSelector.Builder.() -> Unit) {
            namespaceSelector = LabelSelector.Builder().apply(scope).build()
        }

        fun objectSelector(scope: LabelSelector.Builder.() -> Unit) {
            objectSelector = LabelSelector.Builder().apply(scope).build()
        }

        fun resourceRules(scope: NamedRuleWithOperations.Group.() -> Unit) {
            resourceRules = NamedRuleWithOperations.Group().apply(scope).rules()
        }

        override fun build(): MatchResources {
            return MatchResources(
                excludeResourceRules = excludeResourceRules,
                matchPolicy = matchPolicy,
                namespaceSelector = namespaceSelector,
                objectSelector = objectSelector,
                resourceRules = resourceRules
            )
        }
    }
}