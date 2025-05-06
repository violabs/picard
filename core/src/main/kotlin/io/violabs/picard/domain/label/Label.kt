package io.violabs.picard.domain.label

import io.violabs.picard.common.YAMLMap

data class Label(
    override val key: String,
    override val value: String
) : YAMLMap {
    class Group {
        private var labels: MutableList<Label> = mutableListOf()

        fun labels(): MutableList<Label>? {
            return labels
        }

        fun label(key: String, value: String) {
            labels.add(Label(key, value))
        }
    }
}