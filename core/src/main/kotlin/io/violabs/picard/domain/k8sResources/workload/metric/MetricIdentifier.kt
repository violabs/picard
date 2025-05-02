package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.LabelSelector

data class MetricIdentifier(
    val name: String,
    val selector: LabelSelector? = null
) {
    class Builder : DSLBuilder<MetricIdentifier> {
        var name: String? = null
        private var selector: LabelSelector? = null

        fun selector(block: LabelSelector.Builder.() -> Unit) {
            selector = LabelSelector.Builder().apply(block).build()
        }

        override fun build(): MetricIdentifier {
            return MetricIdentifier(
                name = requireNotNull(name),
                selector = selector
            )
        }
    }
}