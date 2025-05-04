package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.HTTPVerb

data class NonResourceRule(
    val verbs: List<HTTPVerb>,
    val nonResourceURLs: String? = null
) {
    class Builder : DSLBuilder<NonResourceRule> {
        private var verbs: List<HTTPVerb>? = null
        var nonResourceURLs: String? = null

        fun verbs(vararg verbs: HTTPVerb) {
            this.verbs = verbs.toList()
        }

        override fun build(): NonResourceRule {
            return NonResourceRule(
                verbs = vRequireNotEmpty(this::verbs),
                nonResourceURLs = nonResourceURLs
            )
        }
    }

    class Group : BuilderGroup<NonResourceRule, Builder>(Builder()) {
        fun rules(): List<NonResourceRule>? = items()

        fun rule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}
