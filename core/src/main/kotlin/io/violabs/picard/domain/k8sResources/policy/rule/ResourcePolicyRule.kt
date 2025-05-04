package io.violabs.picard.domain.k8sResources.policy.rule

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class ResourcePolicyRule(
    val apiGroups: List<String>,
    val resources: List<String>,
    val verbs: List<String>,
    val clusterScope: Boolean? = null,
    val namespaces: List<String>? = null
) {
    class Builder : DSLBuilder<ResourcePolicyRule> {
        private var apiGroups: List<String>? = null
        private var resources: List<String>? = null
        private var verbs: List<String>? = null
        private var clusterScope: Boolean? = null
        private var namespaces: List<String>? = null

        fun apiGroups(vararg groups: String) {
            apiGroups = groups.toList()
        }

        fun resources(vararg resources: String) {
            this.resources = resources.toList()
        }

        fun verbs(vararg verbs: String) {
            this.verbs = verbs.toList()
        }

        fun clusterScope(value: Boolean = true) {
            clusterScope = value
        }

        fun namespaces(vararg namespaces: String) {
            this.namespaces = namespaces.toList()
        }

        override fun build(): ResourcePolicyRule {
            return ResourcePolicyRule(
                apiGroups = vRequireNotEmpty(this::apiGroups),
                resources = vRequireNotEmpty(this::resources),
                verbs = vRequireNotEmpty(this::verbs),
                clusterScope = clusterScope,
                namespaces = namespaces
            )
        }
    }

    class Group : BuilderGroup<ResourcePolicyRule, Builder>(Builder()) {
        fun rules(): List<ResourcePolicyRule>? = items()

        fun rule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}