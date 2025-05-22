package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.gatewayConfig.GatewayConfig

@GeneratedDsl
class LokiConfig(
    val schemaConfig: SchemaConfig,
    val deploymentMode: DeploymentMode,
    val backend: DeployConfig,
    val read: DeployConfig,
    val write: DeployConfig,
    val minio: MinIOConfig,
    val gateway: GatewayConfig
)