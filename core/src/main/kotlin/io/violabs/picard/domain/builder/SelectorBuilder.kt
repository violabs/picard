package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Label
import io.violabs.picard.domain.Selector

class SelectorBuilder : Builder<Selector> {
    private var matchLabels: List<Label>? = null

    override fun build(): Selector {
        return Selector(
            matchLabels = requireNotNull(matchLabels) { "Please provide a labels" }
        )
    }

    fun matchLabels(scope: LabelsBuilder.() -> Unit) {
        matchLabels = scopedBuild(::LabelsBuilder, scope)
    }
}