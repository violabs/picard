package io.violabs.picard.domain.label

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.serialization.ListAsMapSerializer

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/label-selector/#LabelSelector
 * import "k8s.io/apimachinery/pkg/apis/meta/v1"
 */
data class LabelSelector(
    val matchExpressions: List<LabelSelectorRequirement>? = null,
    @JsonSerialize(using = ListAsMapSerializer::class)
    val matchLabels: List<Label>? = null
) {

    class Builder : DslBuilder<LabelSelector> {
        private var matchExpressions: List<LabelSelectorRequirement>? = null
        private var matchLabels: List<Label>? = null

        fun matchExpressions(scope: LabelSelectorRequirement.Group.() -> Unit) {
            matchExpressions = LabelSelectorRequirement.Group().apply(scope).requirements()
        }

        fun matchLabels(scope: Label.Group.() -> Unit) {
            matchLabels = Label.Group().apply(scope).labels()
        }

        override fun build(): LabelSelector {
            return LabelSelector(
                matchExpressions,
                matchLabels
            )
        }
    }

    class Group : BuilderGroup<LabelSelector, Builder>(Builder()) {
        fun selectors(): List<LabelSelector>? = items()

        fun selector(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}