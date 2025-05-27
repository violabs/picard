package io.violabs.picard.starCharts.loki

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.gatewayConfig.GatewayConfig

@GeneratedDsl(
    isRoot = true
)
class LokiConfig(
    val schemaConfig: SchemaConfig,
    val deploymentMode: DeploymentMode,
    val backend: DeployConfig,
    val read: DeployConfig,
    val write: DeployConfig,
    val minio: MinIOConfig,
    val gateway: GatewayConfig
)