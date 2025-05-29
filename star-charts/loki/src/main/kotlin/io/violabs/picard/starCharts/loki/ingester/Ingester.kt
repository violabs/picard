package io.violabs.picard.starCharts.loki.ingester

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.BackoffConfig
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
class Ingester(
    /**
     * # Configures how the lifecycle of the ingester will operate and where it will
     * # register for discovery.
     */
    val lifecycle: Lifecycler? = null,
    /**
     * # How many flushes can happen concurrently from each stream.
     * # CLI flag: -ingester.concurrent-flushes
     * [concurrent_flushes: <int> | default = 32]
     */
    val concurrentFlushes: Int? = null,
    /**
     * # How often should the ingester see if there are any blocks to flush. The first
     * # flush check is delayed by a random time up to 0.8x the flush check period.
     * # Additionally, there is +/- 1% jitter added to the interval.
     * # CLI flag: -ingester.flush-check-period
     * [flush_check_period: <duration> | default = 30s]
     */
    val flushCheckPeriod: Duration? = null,
    val flushOpBackoff: BackoffConfig? = null,
    /**
     * # The timeout for an individual flush. Will be retried up to
     * # `flush-op-backoff-retries` times.
     * # CLI flag: -ingester.flush-op-timeout
     * [flush_op_timeout: <duration> | default = 10m]
     */
    val flushOpTimout: Duration? = null,
    /**
     * # How long chunks should be retained in-memory after they've been flushed.
     * # CLI flag: -ingester.chunks-retain-period
     * [chunk_retain_period: <duration> | default = 0s]
     */
    val chunkRetainPeriod: Duration? = null,
    /**
     * # How long chunks should sit in-memory with no updates before being flushed if
     * # they don't hit the max block size. This means that half-empty chunks will
     * # still be flushed after a certain period as long as they receive no further
     * # activity.
     * # CLI flag: -ingester.chunks-idle-period
     * [chunk_idle_period: <duration> | default = 30m]
     */
    val chunkIdlePeriod: Duration? = null,
    /**
     * # The targeted _uncompressed_ size in bytes of a chunk block When this threshold
     * # is exceeded the head block will be cut and compressed inside the chunk.
     * # CLI flag: -ingester.chunks-block-size
     * [chunk_block_size: <int> | default = 262144]
     */
    val chunkBlockSize: Int? = null,
    /**
     * # A target _compressed_ size in bytes for chunks. This is a desired size not an
     * # exact size, chunks may be slightly bigger or significantly smaller if they get
     * # flushed for other reasons (e.g. chunk_idle_period). A value of 0 creates
     * # chunks with a fixed 10 blocks, a non zero value will create chunks with a
     * # variable number of blocks to meet the target size.
     * # CLI flag: -ingester.chunk-target-size
     * [chunk_target_size: <int> | default = 1572864]
     */
    val chunkTargetSize: Int? = null,
    /**
     * # The algorithm to use for compressing chunk. (none, gzip, lz4-64k, snappy,
     * # lz4-256k, lz4-1M, lz4, flate, zstd)
     * # CLI flag: -ingester.chunk-encoding
     * [chunk_encoding: <string> | default = "gzip"]
     */
    val chunkEncoding: String? = null,
    /**
     * # The maximum duration of a timeseries chunk in memory. If a timeseries runs for
     * # longer than this, the current chunk will be flushed to the store and a new
     * # chunk created.
     * # CLI flag: -ingester.max-chunk-age
     * [max_chunk_age: <duration> | default = 2h]
     */
    val maxChunkAge: Duration? = null,
    /**
     * # Forget about ingesters having heartbeat timestamps older than
     * # `ring.kvstore.heartbeat_timeout`. This is equivalent to clicking on the
     * # `/ring` `forget` button in the UI: the ingester is removed from the ring. This
     * # is a useful setting when you are sure that an unhealthy node won't return. An
     * # example is when not using stateful sets or the equivalent. Use
     * # `memberlist.rejoin_interval` > 0 to handle network partition cases when using
     * # a memberlist.
     * # CLI flag: -ingester.autoforget-unhealthy
     * [autoforget_unhealthy: <boolean> | default = false]
     */
    val autoforgetUnhealthy: Boolean? = null,
    /**
     * # Parameters used to synchronize ingesters to cut chunks at the same moment.
     * # Sync period is used to roll over incoming entry to a new chunk. If chunk's
     * # utilization isn't high enough (eg. less than 50% when sync_min_utilization is
     * # set to 0.5), then this chunk rollover doesn't happen.
     * # CLI flag: -ingester.sync-period
     * [sync_period: <duration> | default = 1h]
     */
    val syncPeriod: Duration? = null,
    /**
     * # Minimum utilization of chunk when doing synchronization.
     * # CLI flag: -ingester.sync-min-utilization
     * [sync_min_utilization: <float> | default = 0.1]
     */
    val syncMinUtilization: Float? = null,
    /**
     * # The maximum number of errors a stream will report to the user when a push
     * # fails. 0 to make unlimited.
     * # CLI flag: -ingester.max-ignored-stream-errors
     * [max_returned_stream_errors: <int> | default = 10]
     */
    val maxReturnedStreamErrors: Int? = null,
    /**
     * # How far back should an ingester be allowed to query the store for data, for
     * # use only with boltdb-shipper/tsdb index and filesystem object store. -1 for
     * # infinite.
     * # CLI flag: -ingester.query-store-max-look-back-period
     * [query_store_max_look_back_period: <duration> | default = 0s]
     */
    val queryStoreMaxLookBackPeriod: Duration? = null,
    /**
     * # The ingester WAL (Write Ahead Log) records incoming logs and stores them on
     * # the local file systems in order to guarantee persistence of acknowledged data
     * # in the event of a process crash.
     */
    val wal: Wal? = null,
    /**
     * # Shard factor used in the ingesters for the in process reverse index. This MUST
     * # be evenly divisible by ALL schema shard factors or Loki will not start.
     * # CLI flag: -ingester.index-shards
     * [index_shards: <int> | default = 32]
     */
    val indexShards: Int? = null,
    /**
     * # Maximum number of dropped streams to keep in memory during tailing.
     * # CLI flag: -ingester.tailer.max-dropped-streams
     * [max_dropped_streams: <int> | default = 10]
     */
    val maxDroppedStreams: Int? = null,
    /**
     * # Path where the shutdown marker file is stored. If not set and
     * # common.path_prefix is set then common.path_prefix will be used.
     * # CLI flag: -ingester.shutdown-marker-path
     * [shutdown_marker_path: <string> | default = ""]
     */
    val shutdownMarkerPath: String? = null,
    /**
     * # Interval at which the ingester ownedStreamService checks for changes in the
     * # ring to recalculate owned streams.
     * # CLI flag: -ingester.owned-streams-check-interval
     * [owned_streams_check_interval: <duration> | default = 30s]
     */
    val ownedStreamsCheckInterval: Int? = null,
    val kafkaIngestion: KafkaIngestion? = null
)