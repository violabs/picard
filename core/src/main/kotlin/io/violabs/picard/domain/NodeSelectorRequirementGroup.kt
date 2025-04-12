package io.violabs.picard.domain

class NodeSelectorRequirementGroup {
    private val requirements = mutableListOf<NodeSelectorRequirement>()
    fun requirements(): MutableList<NodeSelectorRequirement> {
        return requirements
    }

    fun requirement(scope: NodeSelectorRequirement.Builder.() -> Unit) {
        requirements.add(NodeSelectorRequirement.Builder().apply(scope).build())
    }
}