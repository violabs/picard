package io.violabs.picard.domain.builder

import io.violabs.picard.common.DefaultLogger
import io.violabs.picard.domain.Label

class LabelsBuilder : Builder<List<Label>>, DefaultLogger(LabelsBuilder::class) {
    private val labels = mutableListOf<Label>()

    override fun build(): List<Label> = labels

    fun label(key: String, value: String) {
        labels.add(Label(key, value).also { log.debug("adding label: $it") })
    }

    /**
     * For shared label objects
     */
    fun label(label: Label) {
        log.debug("adding label: $label")
        labels.add(label)
    }
}