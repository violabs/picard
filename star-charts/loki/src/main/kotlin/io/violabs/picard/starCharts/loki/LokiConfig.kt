package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.ingester.Ingester
import io.violabs.picard.starCharts.loki.limitsConfig.LimitsConfig
import io.violabs.picard.starCharts.loki.querier.Querier

@GeneratedDsl
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy::class)
class LokiConfig(
    val schemaConfig: SchemaConfig? = null,
    val ingester: Ingester? = null,
    val querier: Querier? = null,
    val patternIngester: PatternIngester? = null,
    val limitsConfig: LimitsConfig? = null,
)