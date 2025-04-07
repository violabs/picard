package io.violabs.picard.domain

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/label-selector/#LabelSelector
 * import "k8s.io/apimachinery/pkg/apis/meta/v1"
 */
data class LabelSelector(
    val matchExpressions: List<Requirement>? = null,
    val matchLabels: List<Label>? = null
) {

    data class Requirement(
        val key: String,
        val operator: String,
        val values: List<String>? = null
    )
}