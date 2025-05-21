package io.violabs.picard.starCharts.loki.ruler

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.starCharts.loki.AlibabaCloudStorageConfig
import io.violabs.picard.starCharts.loki.AzureStorageConfig
import io.violabs.picard.starCharts.loki.BosStorageConfig
import io.violabs.picard.starCharts.loki.GcsStorageConfig
import io.violabs.picard.starCharts.loki.swiftStorageConfig.SwiftStorageConfig
import io.violabs.picard.starCharts.loki.cosStorageConfig.CosStorageConfig
import io.violabs.picard.starCharts.loki.s3StorageConfig.S3StorageConfig

data class RulerStorage(
    /**
     *   # Method to use for backend rule storage (configdb, azure, gcs, s3, swift,
     *   # local, bos, cos)
     *   # CLI flag: -ruler.storage.type
     *   [type: <string> | default = ""]
     */
    val type: String? = null,
    /**
     *   # Configures backend rule storage for Azure.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [azure: <azure_storage_config>]
     */
    val azure: AzureStorageConfig? = null,
    /**
     *   # Configures backend rule storage for AlibabaCloud Object Storage (OSS).
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [alibabacloud: <alibabacloud_storage_config>]
     */
    @JsonProperty("alibabacloud")
    val alibabaCloud: AlibabaCloudStorageConfig? = null,
    /**
     *   # Configures backend rule storage for GCS.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [gcs: <gcs_storage_config>]
     */
    val gcs: GcsStorageConfig? = null,
    /**
     *   # Configures backend rule storage for S3.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [s3: <s3_storage_config>]
     */
    val s3: S3StorageConfig? = null,
    /**
     *   # Configures backend rule storage for Baidu Object Storage (BOS).
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [bos: <bos_storage_config>]
     */
    val bos: BosStorageConfig? = null,
    /**
     *   # Configures backend rule storage for Swift.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [swift: <swift_storage_config>]
     */
    val swift: SwiftStorageConfig? = null,
    /**
     *   # Configures backend rule storage for IBM Cloud Object Storage (COS).
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [cos: <cos_storage_config>]
     */
    val cos: CosStorageConfig? = null,
    /**
     *   # Configures backend rule storage for a local file system directory.
     *   local:
     *     # Directory to scan for rules
     *     # CLI flag: -ruler.storage.local.directory
     *     [directory: <string> | default = ""]
     */
    val directory: String? = null
)