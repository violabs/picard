package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.gatewayConfig.GatewayConfig

@GeneratedDSL
class LokiConfig(
    val schemaConfig: SchemaConfig,
    val deploymentMode: DeploymentMode,
    val backend: DeployConfig,
    val read: DeployConfig,
    val write: DeployConfig,
    val minio: MinIOConfig,
    val gateway: GatewayConfig
)