package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.ingester.Ingester
import io.violabs.picard.starCharts.loki.limitsConfig.LimitsConfig
import io.violabs.picard.starCharts.loki.querier.Querier
import io.violabs.picard.starCharts.loki.ruler.Ruler

@GeneratedDsl
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy::class)
class LokiConfig(
    val commonConfig: CommonConfig? = null,
    val schemaConfig: SchemaConfig? = null,
    // this is for scalable deployment
    val ingester: Ingester? = null,
    // this is for scalable deployment
    val querier: Querier? = null,
    val patternIngester: PatternIngester? = null,
    val limitsConfig: LimitsConfig? = null,
    // this is for monolith
    val ruler: Ruler? = null,
)