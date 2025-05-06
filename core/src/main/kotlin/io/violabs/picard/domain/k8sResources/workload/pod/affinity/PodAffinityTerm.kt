package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseAffinityTerm
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.label.LabelSelector

data class PodAffinityTerm(
    val topologyKey: String,
    val labelSelector: LabelSelector? = null,
    val matchLabelKeys: List<String>? = null,
    val mismatchLabelKeys: List<String>? = null,
    val namespaceSelector: LabelSelector? = null,
    val namespaces: List<String>? = null
) : BaseAffinityTerm {
    class Builder : DSLBuilder<PodAffinityTerm> {
        var topologyKey: String? = null
        private var labelSelector: LabelSelector? = null
        private var matchLabelKeys: List<String>? = null
        private var mismatchLabelKeys: List<String>? = null
        private var namespaceSelector: LabelSelector? = null
        private var namespaces: List<String>? = null

        fun labelSelector(init: LabelSelector.Builder.() -> Unit) {
            labelSelector = LabelSelector.Builder().apply(init).build()
        }

        fun matchLabelKeys(vararg keys: String) {
            matchLabelKeys = keys.toList()
        }

        fun mismatchLabelKeys(vararg keys: String) {
            mismatchLabelKeys = keys.toList()
        }

        fun namespaceSelector(init: LabelSelector.Builder.() -> Unit) {
            namespaceSelector = LabelSelector.Builder().apply(init).build()
        }

        fun namespaces(vararg namespaces: String) {
            this.namespaces = namespaces.toList()
        }

        override fun build(): PodAffinityTerm {
            return PodAffinityTerm(
                topologyKey = vRequireNotNull(this::topologyKey),
                labelSelector = labelSelector,
                matchLabelKeys = matchLabelKeys,
                mismatchLabelKeys = mismatchLabelKeys,
                namespaceSelector = namespaceSelector,
                namespaces = namespaces
            )
        }
    }

    class Group : BuilderGroup<PodAffinityTerm, Builder>(Builder()) {
        fun terms(): List<PodAffinityTerm>? = items()

        fun addPodAffinityTerm(block: Builder.() -> Unit) {
            add(block)
        }
    }
}