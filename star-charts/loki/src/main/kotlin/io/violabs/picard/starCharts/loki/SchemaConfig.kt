package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.periodConfig.PeriodConfig

@GeneratedDSL
data class SchemaConfig(
    val configs: List<PeriodConfig>
)