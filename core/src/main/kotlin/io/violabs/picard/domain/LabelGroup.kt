package io.violabs.picard.domain

class LabelGroup {
    private var labels: MutableList<Label> = mutableListOf()

    fun labels(): MutableList<Label>? {
        return labels
    }

    fun label(key: String, value: String) {
        labels.add(Label(key, value))
    }
}