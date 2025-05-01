package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.DSLBuilder

data class PodSuccessPolicy(
    val rules: List<PodSuccessPolicyRule>? = null
) {
    class Builder : DSLBuilder<PodSuccessPolicy> {
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