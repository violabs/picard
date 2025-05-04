package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.label.LabelSelector

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
                name = vRequireNotNull(this::name),
                selector = selector
            )
        }
    }
}