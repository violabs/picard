package io.violabs.picard.v2.resources.storage.selector.label

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * A topology selector term represents the result of label queries. A null or empty
 * topology selector term matches no objects. The requirements of them are ANDed.
 * It provides a subset of functionality as NodeSelectorTerm. This is an alpha
 * feature and may change in the future.
 */
@GeneratedDsl(withListGroup = true)
data class TopologySelectorTerm(
    /**
     * Atomic: will be replaced during a merge
     *
     * A list of topology selector requirements by labels.
     */
    val matchLabelExpressions: List<TopologySelectorLabelRequirement>? = null
)