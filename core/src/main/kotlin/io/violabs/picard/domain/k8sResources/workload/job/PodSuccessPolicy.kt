package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.DslBuilder

data class PodSuccessPolicy(
    val rules: List<PodSuccessPolicyRule>? = null
) {
    class Builder : DslBuilder<PodSuccessPolicy> {
        private var rules: List<PodSuccessPolicyRule>? = null

        fun rules(block: PodSuccessPolicyRule.Group.() -> Unit) {
            rules = PodSuccessPolicyRule.Group().apply(block).rules()
        }

        override fun build(): PodSuccessPolicy {
            return PodSuccessPolicy(
                rules = rules
            )
        }
    }
}