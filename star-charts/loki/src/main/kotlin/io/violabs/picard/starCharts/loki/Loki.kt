package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.gatewayConfig.GatewayConfig
import io.violabs.picard.starCharts.loki.minioConfig.MinioConfig

@GeneratedDsl(isRoot = true)
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy::class)
data class Loki(
    @JsonProperty("loki")
    val config: LokiConfig,
    val deploymentMode: DeploymentMode? = null,
    val backend: DeployConfig? = null,
    val read: DeployConfig? = null,
    val write: DeployConfig? = null,
    val minio: MinioConfig? = null,
    val gateway: GatewayConfig? = null
)