package io.violabs.picard.starCharts.loki.schemaConfig

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SchemaConfig(
    val configs: List<PeriodConfig>
)