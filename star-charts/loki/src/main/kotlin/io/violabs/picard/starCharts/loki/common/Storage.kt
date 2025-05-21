package io.violabs.picard.starCharts.loki.common

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.*
import io.violabs.picard.starCharts.loki.common.congestionControl.CongestionControl
import io.violabs.picard.starCharts.loki.cosStorageConfig.COSStorageConfig
import io.violabs.picard.starCharts.loki.s3StorageConfig.S3StorageConfig

@GeneratedDSL
data class Storage(
    /**
     * storage:
     *   # The s3_storage_config block configures the connection to Amazon S3 object
     *   # storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [s3: <s3_storage_config>]
     */
    val s3: S3StorageConfig? = null,
    /**
     *   # The gcs_storage_config block configures the connection to Google Cloud
     *   # Storage object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [gcs: <gcs_storage_config>]
     */
    val gcsStorageConfig: GCSStorageConfig? = null,
    /**
     *   # The azure_storage_config block configures the connection to Azure object
     *   # storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [azure: <azure_storage_config>]
     */
    val azure: AzureStorageConfig? = null,
    /**
     *   # The alibabacloud_storage_config block configures the connection to Alibaba
     *   # Cloud Storage object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [alibabacloud: <alibabacloud_storage_config>]
     */
    @JsonProperty("alibabacloud")
    val alibabaCloud: AlibabaCloudStorageConfig? = null,
    /**
     *   # The bos_storage_config block configures the connection to Baidu Object
     *   # Storage (BOS) object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [bos: <bos_storage_config>]
     */
    val bos: BosStorageConfig? = null,
    /**
     *   # The swift_storage_config block configures the connection to OpenStack Object
     *   # Storage (Swift) object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [swift: <swift_storage_config>]
     */
    val swift: SwiftStorageConfig? = null,
    val fileSystem: FileSystem? = null,
    val hedging: Hedging? = null,
    /**
     *   # The cos_storage_config block configures the connection to IBM Cloud Object
     *   # Storage (COS) backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [cos: <cos_storage_config>]
     */
    val cos: COSStorageConfig? = null,
    val congestionControl: CongestionControl? = null,
    /**
     *   # The thanos_object_store_config block configures the connection to object
     *   # storage backend using thanos-io/objstore clients. This will become the
     *   # default way of configuring object store clients in future releases.
     *   # Currently this is opt-in and takes effect only when `-use-thanos-objstore`
     *   # is set to true.
     *   # The CLI flags prefix for this block configuration is:
     *   # common.storage.object-store
     *   [object_store: <thanos_object_store_config>]
     */
    val thanosObjectStoreConfig: ThanosObjectStoreConfig? = null
)