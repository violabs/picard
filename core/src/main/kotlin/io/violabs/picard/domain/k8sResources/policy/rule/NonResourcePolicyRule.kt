package io.violabs.picard.domain.k8sResources.policy.rule

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class NonResourcePolicyRule(
    val nonResourceURLs: List<String>,
    val verbs: List<String>
) {
    class Builder : DslBuilder<NonResourcePolicyRule> {
        private var nonResourceURLs: List<String>? = null
        private var verbs: List<String>? = null

        fun nonResourceURLs(vararg urls: String) {
            nonResourceURLs = urls.toList()
        }

        fun verbs(vararg verbs: String) {
            this.verbs = verbs.toList()
        }

        override fun build(): NonResourcePolicyRule {
            return NonResourcePolicyRule(
                nonResourceURLs = vRequireNotEmpty(this::nonResourceURLs),
                verbs = vRequireNotEmpty(this::verbs)
            )
        }
    }

    class Group : BuilderGroup<NonResourcePolicyRule, Builder>(Builder()) {
        fun rules(): List<NonResourcePolicyRule>? = items()

        fun rule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}