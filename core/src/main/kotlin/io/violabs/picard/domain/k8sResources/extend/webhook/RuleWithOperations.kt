package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class RuleWithOperations(
    val apiGroups: List<String>? = null,
    val apiVersions: List<String>? = null,
    val operations: List<String>? = null,
    val resources: List<String>? = null,
    val scope: String? = null
) {
    class Builder : DslBuilder<RuleWithOperations> {
        private var apiGroups: List<String>? = null
        private var apiVersions: List<String>? = null
        private var operations: List<String>? = null
        private var resources: List<String>? = null
        var scope: String? = null

        fun apiGroups(vararg groups: String) {
            apiGroups = groups.toList()
        }

        fun apiVersions(vararg versions: String) {
            apiVersions = versions.toList()
        }

        fun operations(vararg ops: String) {
            operations = ops.toList()
        }

        fun resources(vararg res: String) {
            resources = res.toList()
        }

        override fun build(): RuleWithOperations {
            return RuleWithOperations(
                apiGroups = apiGroups,
                apiVersions = apiVersions,
                operations = operations,
                resources = resources,
                scope = scope
            )
        }
    }

    class Group : BuilderGroup<RuleWithOperations, Builder>(Builder()) {
        fun rules(): List<RuleWithOperations>? = items()

        fun addRuleWithOperations(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}