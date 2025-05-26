package io.violabs.picard.starCharts.loki

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.periodConfig.PeriodConfig

@GeneratedDsl
data class SchemaConfig(
    val configs: List<PeriodConfig>
)