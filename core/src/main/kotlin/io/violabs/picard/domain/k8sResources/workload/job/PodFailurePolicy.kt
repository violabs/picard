package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.DSLBuilder

data class PodFailurePolicy(val rules: List<PodFailurePolicyRule>) {
    class Builder : DSLBuilder<PodFailurePolicy> {
        private var rules: List<PodFailurePolicyRule>? = null

        fun rules(block: PodFailurePolicyRule.Group.() -> Unit) {
            rules = PodFailurePolicyRule.Group().apply(block).rules()
        }

        override fun build(): PodFailurePolicy {
            return PodFailurePolicy(
                rules = requireNotNull(rules)
            )
        }
    }
}