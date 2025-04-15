package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.DSLBuilder

data class PolicyRule(
    val verbs: List<String>,
    val apiGroups: List<String>? = null,
    val resources: List<String>? = null,
    val resourceNames: List<String>? = null,
    val nonResourceURLs: List<String>? = null
) {
    class Builder : DSLBuilder<PolicyRule> {
        private var verbs: List<String>? = null
        private var apiGroups: List<String>? = null
        private var resources: List<String>? = null
        private var resourceNames: List<String>? = null
        private var nonResourceURLs: List<String>? = null

        fun verbs(vararg verbs: String) {
            this.verbs = verbs.toList()
        }

        fun apiGroups(vararg apiGroups: String) {
            this.apiGroups = apiGroups.toList()
        }

        fun resources(vararg resources: String) {
            this.resources = resources.toList()
        }

        fun resourceNames(vararg resourceNames: String) {
            this.resourceNames = resourceNames.toList()
        }

        fun nonResourceURLs(vararg nonResourceURLs: String) {
            this.nonResourceURLs = nonResourceURLs.toList()
        }

        override fun build(): PolicyRule {
            return PolicyRule(
                verbs = requireNotNull(verbs),
                apiGroups = apiGroups,
                resources = resources,
                resourceNames = resourceNames,
                nonResourceURLs = nonResourceURLs
            )
        }
    }
}
