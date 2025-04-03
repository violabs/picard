package io.violabs.picard.domain.builder

import io.violabs.picard.common.DefaultLogger
import io.violabs.picard.domain.Label
import io.violabs.picard.domain.ObjectMetadata

class MetadataBuilder : Builder<ObjectMetadata>, DefaultLogger(MetadataBuilder::class) {
    var name: String? = null
        set(value) {
            field = value
            log.debug("name: $value")
        }
    private var labels: List<Label>? = null

    override fun build(): ObjectMetadata {
        return ObjectMetadata(
            name = name,
            labels = labels
        )
    }

    fun labels(block: LabelsBuilder.() -> Unit) {
        labels = scopedBuild(::LabelsBuilder, block)
        log.debug("labels: $labels")
    }
}