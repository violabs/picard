package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class PodSuccessPolicyRule(
    val succeededCount: Int? = null,
    val succeededIndexes: String? = null
) {
    class Builder : DSLBuilder<PodSuccessPolicyRule> {
        var succeededCount: Int? = null
        var succeededIndexes: String? = null

        override fun build(): PodSuccessPolicyRule {
            return PodSuccessPolicyRule(
                succeededCount = succeededCount,
                succeededIndexes = succeededIndexes
            )
        }
    }

    class Group : BuilderGroup<PodSuccessPolicyRule, Builder>(Builder()) {
        fun rules(): List<PodSuccessPolicyRule>? = items()

        fun rule(block: Builder.() -> Unit) {
            add(block)
        }
    }
}