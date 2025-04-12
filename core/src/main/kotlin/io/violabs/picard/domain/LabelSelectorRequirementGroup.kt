package io.violabs.picard.domain

class LabelSelectorRequirementGroup {
    private val requirements = mutableListOf<LabelSelectorRequirement>()
    fun requirements(): MutableList<LabelSelectorRequirement> {
        return requirements
    }

    fun requirement(scope: LabelSelectorRequirement.Builder.() -> Unit) {
        requirements.add(LabelSelectorRequirement.Builder().apply(scope).build())
    }
}