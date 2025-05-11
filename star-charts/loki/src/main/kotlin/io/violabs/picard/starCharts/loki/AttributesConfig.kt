package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
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