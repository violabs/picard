package io.violabs.picard.starCharts.loki.periodConfig

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class Index(
    /**
     * Path prefix for index tables. Prefix always needs to end with a path
     * delimiter '/', except when the prefix is empty.
     */
    @JsonProperty("path_prefix")
    val pathPrefix: String? = null,
    /**
     * Table prefix for all period tables.
     */
    val prefix: String? = null,
    /**
     * Table period.
     */
    val period: Quantity? = null,
    /**
     * A map to be added to all managed tables.
     */
    val tags: Map<String, String>? = null
)