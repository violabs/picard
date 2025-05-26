package io.violabs.picard.starCharts.loki

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class AttributesConfig(
    /**
     * # Configures action to take on matching attributes. It allows one of
     * # [structured_metadata, drop] for all attribute types. It additionally allows
     * # index_label action for resource attributes
     * [action: <string> | default = ""]
     */
    val action: String? = null,
    /**
     * # List of attributes to configure how to store them or drop them altogether
     * [attributes: <list of strings>]
     */
    val attributes: List<String>? = null,
    /**
     * # Regex to choose attributes to configure how to store them or drop them
     * # altogether
     * [regex: <Regexp>]
     */
    val regex: Regex? = null
)