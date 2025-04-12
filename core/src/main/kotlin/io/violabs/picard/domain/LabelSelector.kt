package io.violabs.picard.domain

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/label-selector/#LabelSelector
 * import "k8s.io/apimachinery/pkg/apis/meta/v1"
 */
data class LabelSelector(
    val matchExpressions: List<LabelSelectorRequirement>? = null,
    val matchLabels: List<Label>? = null
) {

    class Builder : DslBuilder<LabelSelector> {
        private var matchExpressions: List<LabelSelectorRequirement>? = null
        private var matchLabels: List<Label>? = null

        fun matchExpressions(scope: LabelSelectorRequirementGroup.() -> Unit) {
            matchExpressions = LabelSelectorRequirementGroup().apply(scope).requirements()
        }

        fun matchLabels(scope: LabelGroup.() -> Unit) {
            matchLabels = LabelGroup().apply(scope).labels()
        }

        override fun build(): LabelSelector {
            return LabelSelector(
                matchExpressions,
                matchLabels
            )
        }
    }
}