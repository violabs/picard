package io.violabs.picard.domain

data class LabelSelectorRequirement(
    val key: String,
    val operator: String,
    val values: List<String>? = null
) {
    class Builder : DslBuilder<LabelSelectorRequirement> {
        var key: String? = null
        var operator: String? = null
        private var values: List<String>? = null

        fun values(vararg values: String) {
            this.values = values.toList()
        }

        override fun build(): LabelSelectorRequirement {
            return LabelSelectorRequirement(
                key = requireNotNull(key),
                operator = requireNotNull(operator),
                values = values
            )
        }
    }
}