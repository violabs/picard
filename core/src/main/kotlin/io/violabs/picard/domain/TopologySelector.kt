package io.violabs.picard.domain

object TopologySelector {
    data class Term(
        val matchLabelExpressions: List<LabelRequirement>? = null
    )

    class LabelRequirement(val key: String, val values: List<String>)
}