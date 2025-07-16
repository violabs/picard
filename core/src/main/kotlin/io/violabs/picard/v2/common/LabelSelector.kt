package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * import "k8s.io/apimachinery/pkg/apis/meta/v1"
 *
 * A label query over a set of resources. The result of matchLabels and matchExpressions are ANDed. An empty label selector matches all objects. A null label selector matches no objects.
 */
@GeneratedDsl
data class LabelSelector(
    /**
     * matchExpressions ([]LabelSelectorRequirement)
     *
     * Atomic: will be replaced during a merge
     *
     * matchExpressions is a list of label selector requirements. The requirements are ANDed.
     *
     * A label selector requirement is a selector that contains values, a key, and an operator that relates the key and values.
     */
    val matchExpressions: List<LabelSelectorRequirement>? = null,
    /**
     * matchLabels (map[string]string)
     *
     * matchLabels is a map of {key,value} pairs. A single {key,value} in the matchLabels map is equivalent to an element of matchExpressions, whose key field is "key", the operator is "In", and the values array contains only "value". The requirements are ANDed.
     */
    val matchLabels: Map<String, String>? = null
)