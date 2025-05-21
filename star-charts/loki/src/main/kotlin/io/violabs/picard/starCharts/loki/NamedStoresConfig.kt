package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.cosStorageConfig.CosStorageConfig

@GeneratedDSL
class NamedStoresConfig(
    val aws: Map<String, AwsStorageConfig>? = null,
    val azure: Map<String, AzureStorageConfig>? = null,
    val bos: Map<String, BosStorageConfig>? = null,
    val filesystem: Map<String, LocalStorageConfig>? = null,
    val gcs: Map<String, GcsStorageConfig>? = null,
    val alibabacloud: Map<String, AlibabaCloudStorageConfig>? = null,
    val swift: Map<String, SwiftStorageConfig>? = null,
    val cos: Map<String, CosStorageConfig>? = null,
)