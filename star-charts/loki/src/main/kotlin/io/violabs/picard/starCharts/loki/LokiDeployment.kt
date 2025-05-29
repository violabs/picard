package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.gatewayConfig.GatewayConfig
import io.violabs.picard.starCharts.loki.minioConfig.MinioConfig

/**
 * todo: Break into 3 different deployment modes.
 * requires additional
 */
@GeneratedDsl(isRoot = true)
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy::class)
data class LokiDeployment(
    @JsonProperty("loki")
    val config: LokiConfig,
    val deploymentMode: DeploymentMode? = null,
    val singleBinary: DeployConfig? = null,
    val backend: DeployConfig? = null,
    val read: DeployConfig? = null,
    val write: DeployConfig? = null,
    val ingester: DeployConfig? = null,
    val querier: DeployConfig? = null,
    val queryFrontend: DeployConfig? = null,
    val queryScheduler: DeployConfig? = null,
    val distributor: DeployConfig? = null,
    val compactor: DeployConfig? = null,
    val indexGateway: DeployConfig? = null,
    val bloomCompactor: DeployConfig? = null,
    val bloomGateway: DeployConfig? = null,
    val minio: MinioConfig? = null,
    val gateway: GatewayConfig? = null
)