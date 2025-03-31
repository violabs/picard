package io.violabs.picard.domain.builder

import io.violabs.picard.common.DefaultLogger
import io.violabs.picard.domain.Label
import io.violabs.picard.domain.Metadata

class MetadataBuilder : Builder<Metadata>, DefaultLogger(MetadataBuilder::class) {
    var name: String? = null
    private var labels: List<Label>? = null

    override fun build(): Metadata = Metadata(
        name = name,
        labels = labels
    )

    fun labels(block: LabelsBuilder.() -> Unit) {
        labels = scopedBuild(::LabelsBuilder, block)
        log.debug("labels: $labels")
    }
}