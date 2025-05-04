package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class NamedRuleWithOperations(
    val apiGroups: List<String>? = null,
    val apiVersions: List<String>? = null,
    val operations: List<String>? = null,
    val resourceNames: List<String>? = null,
    val resources: List<String>? = null,
    val scope: String? = null
) {
    class Builder : DSLBuilder<NamedRuleWithOperations> {
        private var apiGroups: List<String>? = null
        private var apiVersions: List<String>? = null
        private var operations: List<String>? = null
        private var resourceNames: List<String>? = null
        private var resources: List<String>? = null
        var scope: String? = null

        fun apiGroups(vararg apiGroups: String) {
            this.apiGroups = apiGroups.toList()
        }

        fun apiVersions(vararg apiVersion: String) {
            this.apiVersions = apiVersion.toList()
        }

        fun operations(vararg operations: String) {
            this.operations = operations.toList()
        }

        fun resourceNames(vararg resourceNames: String) {
            this.resourceNames = resourceNames.toList()
        }

        fun resources(vararg resources: String) {
            this.resources = resources.toList()
        }

        override fun build(): NamedRuleWithOperations {
            return NamedRuleWithOperations(
                apiGroups = apiGroups,
                apiVersions = apiVersions,
                operations = operations,
                resourceNames = resourceNames,
                resources = resources,
                scope = scope
            )
        }
    }

    class Group : BuilderGroup<NamedRuleWithOperations, Builder>(Builder()) {
        fun rules(): List<NamedRuleWithOperations>? = items()

        fun rule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}