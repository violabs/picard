package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.HTTPVerb

data class ResourceRule(
    val verbs: List<HTTPVerb>,
    val apiGroups: List<String>? = null,
    val resourceNames: String? = null,
    val resources: String? = null
) {
    class Builder : DSLBuilder<ResourceRule> {
        private var verbs: List<HTTPVerb>? = null
        private var apiGroups: List<String>? = null
        var resourceNames: String? = null
        var resources: String? = null

        fun verbs(vararg verbs: HTTPVerb) {
            this.verbs = verbs.toList()
        }

        fun apiGroups(vararg apiGroups: String) {
            this.apiGroups = apiGroups.toList()
        }

        override fun build(): ResourceRule {
            return ResourceRule(
                verbs = vRequireNotEmpty(this::verbs),
                apiGroups = apiGroups,
                resourceNames = resourceNames,
                resources = resources
            )
        }
    }

    class Group : BuilderGroup<ResourceRule, Builder>(Builder()) {
        fun rules(): List<ResourceRule>? = items()

        fun rule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}
