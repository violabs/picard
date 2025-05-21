package io.violabs.picard.starCharts.loki.storageConfig

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.starCharts.loki.AlibabaCloudStorageConfig
import io.violabs.picard.starCharts.loki.AwsStorageConfig
import io.violabs.picard.starCharts.loki.AzureStorageConfig
import io.violabs.picard.starCharts.loki.BosStorageConfig
import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.GcsStorageConfig
import io.violabs.picard.starCharts.loki.NamedStoresConfig
import io.violabs.picard.starCharts.loki.cacheConfig.CacheConfig
import io.violabs.picard.starCharts.loki.common.Hedging
import io.violabs.picard.starCharts.loki.common.congestionControl.CongestionControl
import io.violabs.picard.starCharts.loki.cosStorageConfig.CosStorageConfig

class StorageConfig(
    /**
     * # The alibabacloud_storage_config block configures the connection to Alibaba
     * # Cloud Storage object storage backend.
     * [alibabacloud: <alibabacloud_storage_config>]
     */
    @JsonProperty("alibabacloud")
    val alibabaCloud: AlibabaCloudStorageConfig? = null,
    /**
     * # The aws_storage_config block configures the connection to dynamoDB and S3
     * # object storage. Either one of them or both can be configured.
     * [aws: <aws_storage_config>]
     */
    val aws: AwsStorageConfig? = null,
    /**
     * # The azure_storage_config block configures the connection to Azure object
     * # storage backend.
     * [azure: <azure_storage_config>]
     */
    val azure: AzureStorageConfig? = null,
    /**
     * # The bos_storage_config block configures the connection to Baidu Object Storage
     * # (BOS) object storage backend.
     * [bos: <bos_storage_config>]
     */
    val bos: BosStorageConfig? = null,
    /**
     * # Deprecated: Configures storing indexes in Bigtable. Required fields only
     * # required when bigtable is defined in config.
     * bigtable:
     */
    @JsonProperty("bigtable")
    val bigTable: BigTable? = null,
    /**
     * # Configures storing chunks in GCS. Required fields only required when gcs is
     * # defined in config.
     * [gcs: <gcs_storage_config>]
     */
    val gcs: GcsStorageConfig? = null,
    val hedging: Hedging? = null,
    /**
     * # Configures additional object stores for a given storage provider.
     * # Supported stores: aws, azure, bos, filesystem, gcs, swift.
     * # Example:
     * # ```yaml
     * #     storage_config:
     * #       named_stores:
     * #         aws:
     * #           store-1:
     * #             endpoint: s3://foo-bucket
     * #             region: us-west1
     * # ```
     * # Named store from this example can be used by setting object_store to store-1
     * # in period_config.
     * [named_stores: <named_stores_config>]
     */
    val namedStores: NamedStoresConfig? = null,
    /**
     * # The cos_storage_config block configures the connection to IBM Cloud Object
     * # Storage (COS) backend.
     * [cos: <cos_storage_config>]
     */
    val cos: CosStorageConfig? = null,
    /**
     * # Cache validity for active index entries. Should be no higher than
     * # -ingester.max-chunk-idle.
     * # CLI flag: -store.index-cache-validity
     * [index_cache_validity: <duration> | default = 5m]
     */
    val indexCacheValidity: Duration? = null,
    /**
     * congestion_control:
     *   # Use storage congestion control (default: disabled).
     *   # CLI flag: -store.congestion-control.enabled
     *   [enabled: <boolean> | default = false]
     */
    val congestionControl: CongestionControl? = null,
    /**
     * # The cache_config block configures the cache backend for a specific Loki
     * # component.
     * # The CLI flags prefix for this block configuration is: store.index-cache-read
     * [index_queries_cache_config: <cache_config>]
     */
    val indexQueriesCacheConfig: CacheConfig? = null,
    /**
     * # Disable broad index queries which results in reduced cache usage and faster
     * # query performance at the expense of somewhat higher QPS on the index store.
     * # CLI flag: -store.disable-broad-index-queries
     * [disable_broad_index_queries: <boolean> | default = false]
     */
    val disableBroadIndexQueries: Boolean? = null,
    /**
     * # Maximum number of parallel chunk reads.
     * # CLI flag: -store.max-parallel-get-chunk
     * [max_parallel_get_chunk: <int> | default = 150]
     */
    val maxParallelGetChunk: Int? = null,
    /**
     * # Enables the use of thanos-io/objstore clients for connecting to object
     * # storage. When set to true, the configuration inside
     * # `storage_config.object_store` or `common.storage.object_store` block takes
     * # effect.
     * # CLI flag: -use-thanos-objstore
     * [use_thanos_objstore: <boolean> | default = false]
     */
    @JsonProperty("useThanosObjstore")
    val useThanosObjStore: Boolean? = null,
    val objectStore: ObjectStore? = null,
    /**
     * # The maximum number of chunks to fetch per batch.
     * # CLI flag: -store.max-chunk-batch-size
     * [max_chunk_batch_size: <int> | default = 50]
     */
    val maxChunkBatchSize: Int? = null,
    val indexGatewayClient: IndexGatewayClient? = null,

    /**
     * # Configures storing index in an Object Store
     * # (GCS/S3/Azure/Swift/COS/Filesystem) in a prometheus TSDB-like format. Required
     * # fields only required when TSDB is defined in config.
     * tsdb_shipper:
     */
    val tsdbShipper: TsdbShipper? = null
)