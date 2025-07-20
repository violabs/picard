package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.DslBuilder

data class PodFailurePolicy(val rules: List<PodFailurePolicyRule>) {
    class Builder : DslBuilder<PodFailurePolicy> {
        private var rules: List<PodFailurePolicyRule>? = null

        fun rules(block: PodFailurePolicyRule.Group.() -> Unit) {
            rules = PodFailurePolicyRule.Group().apply(block).rules()
        }

        override fun build(): PodFailurePolicy {
            return PodFailurePolicy(
                rules = vRequireNotEmpty(this::rules)
            )
        }
    }
}