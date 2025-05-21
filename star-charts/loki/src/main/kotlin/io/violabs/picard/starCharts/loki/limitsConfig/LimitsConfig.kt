package io.violabs.picard.starCharts.loki.limitsConfig

import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.Header
import io.violabs.picard.starCharts.loki.distributor.OTLPConfig
import java.time.LocalDateTime

class LimitsConfig(
    /**
     * # Whether the ingestion rate limit should be applied individually to each
     * # distributor instance (local), or evenly shared across the cluster (global).
     * # The ingestion rate strategy cannot be overridden on a per-tenant basis.
     * # - local: enforces the limit on a per distributor basis. The actual effective
     * # rate limit will be N times higher, where N is the number of distributor
     * # replicas.
     * # - global: enforces the limit globally, configuring a per-distributor local
     * # rate limiter as 'ingestion_rate / N', where N is the number of distributor
     * # replicas (it's automatically adjusted if the number of replicas change). The
     * # global strategy requires the distributors to form their own ring, which is
     * # used to keep track of the current number of healthy distributor replicas.
     * # CLI flag: -distributor.ingestion-rate-limit-strategy
     * [ingestion_rate_strategy: <string> | default = "global"]
     */
    val ingestionRateStrategy: String? = null,
    /**
     * # Per-user ingestion rate limit in sample size per second. Sample size includes
     * # size of the logs line and the size of structured metadata labels. Units in MB.
     * # CLI flag: -distributor.ingestion-rate-limit-mb
     * [ingestion_rate_mb: <float> | default = 4]
     */
    val ingestionRateMB: Float? = null,
    /**
     * # Per-user allowed ingestion burst size (in sample size). Units in MB. The burst
     * # size refers to the per-distributor local rate limiter even in the case of the
     * # 'global' strategy, and should be set at least to the maximum logs size
     * # expected in a single push request.
     * # CLI flag: -distributor.ingestion-burst-size-mb
     * [ingestion_burst_size_mb: <float> | default = 6]
     */
    val ingestionBurstSizeMB: Float? = null,
    /**
     * # Maximum length accepted for label names.
     * # CLI flag: -validation.max-length-label-name
     * [max_label_name_length: <int> | default = 1024]
     */
    val maxLabelNameLength: Int? = null,
    /**
     * # Maximum length accepted for label value. This setting also applies to the
     * # metric name.
     * # CLI flag: -validation.max-length-label-value
     * [max_label_value_length: <int> | default = 2048]
     */
    val maxLabelValueLength: Int? = null,
    /**
     *
     * # Maximum number of label names per series.
     * # CLI flag: -validation.max-label-names-per-series
     * [max_label_names_per_series: <int> | default = 15]
     */
    val maxLabelNamesPerSeries: Int? = null,
    /**
     * # Whether or not old samples will be rejected.
     * # CLI flag: -validation.reject-old-samples
     * [reject_old_samples: <boolean> | default = true]
     */
    val rejectOldSamples: Boolean? = null,
    /**
     * # Maximum accepted sample age before rejecting.
     * # CLI flag: -validation.reject-old-samples.max-age
     * [reject_old_samples_max_age: <duration> | default = 1w]
     */
    val rejectOldSamplesMaxAge: Duration? = null,
    /**
     * # Duration which table will be created/deleted before/after it's needed; we
     * # won't accept sample from before this time.
     * # CLI flag: -validation.create-grace-period
     * [creation_grace_period: <duration> | default = 10m]
     */
    val creationGracePeriod: Duration? = null,
    /**
     * # Maximum line size on ingestion path. Example: 256kb. Any log line exceeding
     * # this limit will be discarded unless `distributor.max-line-size-truncate` is
     * # set which in case it is truncated instead of discarding it completely. There
     * # is no limit when unset or set to 0.
     * # CLI flag: -distributor.max-line-size
     * [max_line_size: <int> | default = 256KB]
     */
    val maxLineSize: Int? = null,
    /**
     * # Whether to truncate lines that exceed max_line_size.
     * # CLI flag: -distributor.max-line-size-truncate
     * [max_line_size_truncate: <boolean> | default = false]
     */
    val maxLineSizeTruncated: Boolean? = null,
    /**
     * # Alter the log line timestamp during ingestion when the timestamp is the same
     * # as the previous entry for the same stream. When enabled, if a log line in a
     * # push request has the same timestamp as the previous line for the same stream,
     * # one nanosecond is added to the log line. This will preserve the received order
     * # of log lines with the exact same timestamp when they are queried, by slightly
     * # altering their stored timestamp. NOTE: This is imperfect, because Loki accepts
     * # out of order writes, and another push request for the same stream could
     * # contain duplicate timestamps to existing entries and they will not be
     * # incremented.
     * # CLI flag: -validation.increment-duplicate-timestamps
     * [increment_duplicate_timestamp: <boolean> | default = false]
     */
    val incrementDuplicateTimestamp: Boolean? = null,
    /**
     * # Simulated latency to add to push requests. Used for testing. Set to 0s to
     * # disable.
     * # CLI flag: -limits.simulated-push-latency
     * [simulated_push_latency: <duration> | default = 0s]
     */
    val simulatedPushLatency: Duration? = null,
    /**
     * # Enable experimental support for running multiple query variants over the same
     * # underlying data. For example, running both a rate() and count_over_time()
     * # query over the same range selector.
     * # CLI flag: -limits.enable-multi-variant-queries
     * [enable_multi_variant_queries: <boolean> | default = false]
     */
    val enableMultiVariantQueries: Boolean? = null,
    /**
     * # Experimental: Detect fields from stream labels, structured metadata, or
     * # json/logfmt formatted log line and put them into structured metadata of the
     * # log entry.
     * discover_generic_fields:
     *   [fields: <map of string to list of strings>]
     */
    val discoverGenericFields: Map<String, List<String>>? = null,
    /**
     * # If no service_name label exists, Loki maps a single label from the configured
     * # list to service_name. If none of the configured labels exist in the stream,
     * # label is set to unknown_service. Empty list disables setting the label.
     * # CLI flag: -validation.discover-service-name
     * [discover_service_name: <list of strings> | default = [service app application app_name name app_kubernetes_io_name container container_name k8s_container_name component workload job k8s_job_name]]
     */
    val discoverServiceName: List<String>? = null,
    /**
     * # Discover and add log levels during ingestion, if not present already. Levels
     * # would be added to Structured Metadata with name
     * # level/LEVEL/Level/Severity/severity/SEVERITY/lvl/LVL/Lvl (case-sensitive) and
     * # one of the values from 'trace', 'debug', 'info', 'warn', 'error', 'critical',
     * # 'fatal' (case insensitive).
     * # CLI flag: -validation.discover-log-levels
     * [discover_log_levels: <boolean> | default = true]
     */
    val discoverLogLevels: Boolean? = null,
    /**
     * # Field name to use for log levels. If not set, log level would be detected
     * # based on pre-defined labels as mentioned above.
     * # CLI flag: -validation.log-level-fields
     * [log_level_fields: <list of strings> | default = [level LEVEL Level Severity severity SEVERITY lvl LVL Lvl severity_text Severity_Text SEVERITY_TEXT]]
     */
    val logLevelFields: List<String>? = null,
    /**
     * # Maximum depth to search for log level fields in JSON logs. A value of 0 or
     * # less means unlimited depth. Default is 2 which searches the first 2 levels of
     * # the JSON object.
     * # CLI flag: -validation.log-level-from-json-max-depth
     * [log_level_from_json_max_depth: <int> | default = 2]
     */
    val logLevelFromJSONMaxDepth: Int? = null,
    /**
     * # When true an ingester takes into account only the streams that it owns
     * # according to the ring while applying the stream limit.
     * # CLI flag: -ingester.use-owned-stream-count
     * [use_owned_stream_count: <boolean> | default = false]
     */
    val useOwnedStreamCount: Boolean? = null,
    /**
     * # Maximum number of active streams per user, per ingester. 0 to disable.
     * # CLI flag: -ingester.max-streams-per-user
     * [max_streams_per_user: <int> | default = 0]
     */
    val maxStreamsPerUser: Int? = null,
    /**
     * # Maximum number of active streams per user, across the cluster. 0 to disable.
     * # When the global limit is enabled, each ingester is configured with a dynamic
     * # local limit based on the replication factor and the current number of healthy
     * # ingesters, and is kept updated whenever the number of ingesters change.
     * # CLI flag: -ingester.max-global-streams-per-user
     * [max_global_streams_per_user: <int> | default = 5000]
     */
    val maxGlobalStreamsPerUser: Int? = null,
    /**
     * # Deprecated. When true, out-of-order writes are accepted.
     * # CLI flag: -ingester.unordered-writes
     * [unordered_writes: <boolean> | default = true]
     */
    val unorderedWrites: Boolean? = null,
    /**
     * # Maximum byte rate per second per stream, also expressible in human readable
     * # forms (1MB, 256KB, etc).
     * # CLI flag: -ingester.per-stream-rate-limit
     * [per_stream_rate_limit: <int> | default = 3MB]
     */
    val perStreamRateLimit: Int? = null,
    /**
     * # Maximum burst bytes per stream, also expressible in human readable forms (1MB,
     * # 256KB, etc). This is how far above the rate limit a stream can 'burst' before
     * # the stream is limited.
     * # CLI flag: -ingester.per-stream-rate-limit-burst
     * [per_stream_rate_limit_burst: <int> | default = 15MB]
     */
    val perStreamRateLimitBurst: Int? = null,
    /**
     * # Maximum number of chunks that can be fetched in a single query.
     * # CLI flag: -store.query-chunk-limit
     * [max_chunks_per_query: <int> | default = 2000000]
     */
    val maxChunksPerQuery: Int? = null,
    /**
     * # Limit the maximum of unique series that is returned by a metric query. When
     * # the limit is reached an error is returned.
     * # CLI flag: -querier.max-query-series
     * [max_query_series: <int> | default = 500]
     */
    val maxQuerySeries: Int? = null,
    /**
     * # Limit how far back in time series data and metadata can be queried, up until
     * # lookback duration ago. This limit is enforced in the query frontend, the
     * # querier and the ruler. If the requested time range is outside the allowed
     * # range, the request will not fail, but will be modified to only query data
     * # within the allowed time range. The default value of 0 does not set a limit.
     * # CLI flag: -querier.max-query-lookback
     * [max_query_lookback: <duration> | default = 0s]
     */
    val maxQueryLookback: Duration? = null,
    /**
     * # The limit to length of chunk store queries. 0 to disable.
     * # CLI flag: -store.max-query-length
     * [max_query_length: <duration> | default = 30d1h]
     */
    val maxQueryLength: Duration? = null,
    /**
     * # Limit the length of the [range] inside a range query. Default is 0 or
     * # unlimited
     * # CLI flag: -querier.max-query-range
     * [max_query_range: <duration> | default = 0s]
     */
    val maxQueryRange: Duration? = null,
    /**
     * # Maximum number of queries that will be scheduled in parallel by the frontend.
     * # CLI flag: -querier.max-query-parallelism
     * [max_query_parallelism: <int> | default = 32]
     */
    val maxQueryParallelism: Int? = null,
    /**
     * # Maximum number of queries will be scheduled in parallel by the frontend for
     * # TSDB schemas.
     * # CLI flag: -querier.tsdb-max-query-parallelism
     * [tsdb_max_query_parallelism: <int> | default = 128]
     */
    val tsdbMaxQueryParallelism: Int? = null,
    /**
     * # Target maximum number of bytes assigned to a single sharded query. Also
     * # expressible in human readable forms (1GB, etc). Note: This is a _target_ and
     * # not an absolute limit. The actual limit can be higher, but the query planner
     * # will try to build shards up to this limit.
     * # CLI flag: -querier.tsdb-max-bytes-per-shard
     * [tsdb_max_bytes_per_shard: <int> | default = 600MB]
     */
    val tsdbMaxBytesPerShard: Int? = null,
    /**
     * # sharding strategy to use in query planning. Suggested to use bounded once all
     * # nodes can recognize it.
     * # CLI flag: -limits.tsdb-sharding-strategy
     * [tsdb_sharding_strategy: <string> | default = "power_of_two"]
     */
    val tsdbShardingStrategy: String? = null,
    /**
     * # Precompute chunks for TSDB queries. This can improve query performance at the
     * # cost of increased memory usage by computing chunks once during planning,
     * # reducing index calls.
     * # CLI flag: -querier.tsdb-precompute-chunks
     * [tsdb_precompute_chunks: <boolean> | default = false]
     */
    val tsdbPrecomputeChunks: Boolean? = null,
    /**
     * # Cardinality limit for index queries.
     * # CLI flag: -store.cardinality-limit
     * [cardinality_limit: <int> | default = 100000]
     */
    val cardinalityLimit: Int? = null,
    /**
     * # Maximum number of stream matchers per query.
     * # CLI flag: -querier.max-streams-matcher-per-query
     * [max_streams_matchers_per_query: <int> | default = 1000]
     */
    val maxStreamsMatchersPerQuery: Int? = null,
    /**
     * # Maximum number of concurrent tail requests.
     * # CLI flag: -querier.max-concurrent-tail-requests
     * [max_concurrent_tail_requests: <int> | default = 10]
     */
    val maxConcurrentTailRequests: Int? = null,
    /**
     * # Maximum number of log entries that will be returned for a query.
     * # CLI flag: -validation.max-entries-limit
     * [max_entries_limit_per_query: <int> | default = 5000]
     */
    val maxEntriesLimitPerQuery: Int? = null,
    /**
     * # Most recent allowed cacheable result per-tenant, to prevent caching very
     * # recent results that might still be in flux.
     * # CLI flag: -frontend.max-cache-freshness
     * [max_cache_freshness_per_query: <duration> | default = 10m]
     */
    val maxCacheFreshnessPerQuery: Duration? = null,
    /**
     * # Do not cache metadata request if the end time is within the
     * # frontend.max-metadata-cache-freshness window. Set this to 0 to apply no such
     * # limits. Defaults to 24h.
     * # CLI flag: -frontend.max-metadata-cache-freshness
     * [max_metadata_cache_freshness: <duration> | default = 1d]
     */
    val maxMetadataCacheFreshness: Duration? = null,
    /**
     * # Do not cache requests with an end time that falls within Now minus this
     * # duration. 0 disables this feature (default).
     * # CLI flag: -frontend.max-stats-cache-freshness
     * [max_stats_cache_freshness: <duration> | default = 10m]
     */
    val maxStatsCacheFreshness: Duration? = null,
    /**
     * # Maximum number of queriers that can handle requests for a single tenant. If
     * # set to 0 or value higher than number of available queriers, *all* queriers
     * # will handle requests for the tenant. Each frontend (or query-scheduler, if
     * # used) will select the same set of queriers for the same tenant (given that all
     * # queriers are connected to all frontends / query-schedulers). This option only
     * # works with queriers connecting to the query-frontend / query-scheduler, not
     * # when using downstream URL.
     * # CLI flag: -frontend.max-queriers-per-tenant
     * [max_queriers_per_tenant: <int> | default = 0]
     */
    val maxQueriersPerTenant: Int? = null,
    /**
     * # How much of the available query capacity ("querier" components in distributed
     * # mode, "read" components in SSD mode) can be used by a single tenant. Allowed
     * # values are 0.0 to 1.0. For example, setting this to 0.5 would allow a tenant
     * # to use half of the available queriers for processing the query workload. If
     * # set to 0, query capacity is determined by frontend.max-queriers-per-tenant.
     * # When both frontend.max-queriers-per-tenant and frontend.max-query-capacity are
     * # configured, smaller value of the resulting querier replica count is
     * # considered: min(frontend.max-queriers-per-tenant, ceil(querier_replicas *
     * # frontend.max-query-capacity)). *All* queriers will handle requests for the
     * # tenant if neither limits are applied. This option only works with queriers
     * # connecting to the query-frontend / query-scheduler, not when using downstream
     * # URL. Use this feature in a multi-tenant setup where you need to limit query
     * # capacity for certain tenants.
     * # CLI flag: -frontend.max-query-capacity
     * [max_query_capacity: <float> | default = 0]
     */
    val maxQueryCapacity: Float? = null,
    /**
     *
     * # Number of days of index to be kept always downloaded for queries. Applies only
     * # to per user index in boltdb-shipper index store. 0 to disable.
     * # CLI flag: -store.query-ready-index-num-days
     * [query_ready_index_num_days: <int> | default = 0]
     */
    val queryReadyIndexNumDays: Int? = null,
    /**
     * # Timeout when querying backends (ingesters or storage) during the execution of
     * # a query request. When a specific per-tenant timeout is used, the global
     * # timeout is ignored.
     * # CLI flag: -querier.query-timeout
     * [query_timeout: <duration> | default = 1m]
     */
    val queryTimeout: Duration? = null,
    /**
     * # Split queries by a time interval and execute in parallel. The value 0 disables
     * # splitting by time. This also determines how cache keys are chosen when result
     * # caching is enabled.
     * # CLI flag: -querier.split-queries-by-interval
     * [split_queries_by_interval: <duration> | default = 1h]
     */
    val splitQueriesByInterval: Duration? = null,
    /**
     * # Split metadata queries by a time interval and execute in parallel. The value 0
     * # disables splitting metadata queries by time. This also determines how cache
     * # keys are chosen when label/series result caching is enabled.
     * # CLI flag: -querier.split-metadata-queries-by-interval
     * [split_metadata_queries_by_interval: <duration> | default = 1d]
     */
    val splitMetadataQueriesByInterval: Duration? = null,
    /**
     * # Experimental. Split interval to use for the portion of metadata request that
     * # falls within `recent_metadata_query_window`. Rest of the request which is
     * # outside the window still uses `split_metadata_queries_by_interval`. If set to
     * # 0, the entire request defaults to using a split interval of
     * # `split_metadata_queries_by_interval.`.
     * # CLI flag: -experimental.querier.split-recent-metadata-queries-by-interval
     * [split_recent_metadata_queries_by_interval: <duration> | default = 1h]
     */
    val splitRecentMetadataQueriesByInterval: Duration? = null,
    /**
     * # Experimental. Metadata query window inside which
     * # `split_recent_metadata_queries_by_interval` gets applied, portion of the
     * # metadata request that falls in this window is split using
     * # `split_recent_metadata_queries_by_interval`. The value 0 disables using a
     * # different split interval for recent metadata queries.
     * #
     * # This is added to improve cacheability of recent metadata queries. Query split
     * # interval also determines the interval used in cache key. The default split
     * # interval of 24h is useful for caching long queries, each cache key holding 1
     * # day's results. But metadata queries are often shorter than 24h, to cache them
     * # effectively we need a smaller split interval. `recent_metadata_query_window`
     * # along with `split_recent_metadata_queries_by_interval` help configure a
     * # shorter split interval for recent metadata queries.
     * # CLI flag: -experimental.querier.recent-metadata-query-window
     * [recent_metadata_query_window: <duration> | default = 0s]
     */
    val recentMetadataQueryWindow: Duration? = null,
    /**
     * # Split instant metric queries by a time interval and execute in parallel. The
     * # value 0 disables splitting instant metric queries by time. This also
     * # determines how cache keys are chosen when instant metric query result caching
     * # is enabled.
     * # CLI flag: -querier.split-instant-metric-queries-by-interval
     * [split_instant_metric_queries_by_interval: <duration> | default = 1h]
     */
    val splitInstantMetricQueriesByInterval: Duration? = null,
    /**
     * # Interval to use for time-based splitting when a request is within the
     * # `query_ingesters_within` window; defaults to `split-queries-by-interval` by
     * # setting to 0.
     * # CLI flag: -querier.split-ingester-queries-by-interval
     * [split_ingester_queries_by_interval: <duration> | default = 0s]
     */
    val splitIngesterQueriesByInterval: Duration? = null,
    /**
     * # Limit queries that can be sharded. Queries within the time range of now and
     * # now minus this sharding lookback are not sharded. The default value of 0s
     * # disables the lookback, causing sharding of all queries at all times.
     * # CLI flag: -frontend.min-sharding-lookback
     * [min_sharding_lookback: <duration> | default = 0s]
     */
    val minShardingLookback: Duration? = null,
    /**
     * # Max number of bytes a query can fetch. Enforced in log and metric queries only
     * # when TSDB is used. This limit is not enforced on log queries without filters.
     * # The default value of 0 disables this limit.
     * # CLI flag: -frontend.max-query-bytes-read
     * [max_query_bytes_read: <int> | default = 0B]
     */
    val maxQueryBytesRead: Int? = null,
    /**
     * # Max number of bytes a query can fetch after splitting and sharding. Enforced
     * # in log and metric queries only when TSDB is used. This limit is not enforced
     * # on log queries without filters. The default value of 0 disables this limit.
     * # CLI flag: -frontend.max-querier-bytes-read
     * [max_querier_bytes_read: <int> | default = 150GB]
     */
    val maxQuerierBytesRead: Int? = null,
    /**
     * # Enable log-volume endpoints.
     * # CLI flag: -limits.volume-enabled
     * [volume_enabled: <boolean> | default = true]
     */
    val volumeEnabled: Boolean? = null,
    /**
     * # The maximum number of aggregated series in a log-volume response
     * # CLI flag: -limits.volume-max-series
     * [volume_max_series: <int> | default = 1000]
     */
    val volumeMaxSeries: Int? = null,
    /**
     * # Maximum number of rules per rule group per-tenant. 0 to disable.
     * # CLI flag: -ruler.max-rules-per-rule-group
     * [ruler_max_rules_per_rule_group: <int> | default = 0]
     */
    val rulerMaxRulesPerRuleGroup: Int? = null,
    /**
     * # Maximum number of rule groups per-tenant. 0 to disable.
     * # CLI flag: -ruler.max-rule-groups-per-tenant
     * [ruler_max_rule_groups_per_tenant: <int> | default = 0]
     */
    val rulerMaxRuleGroupsPerTenant: Int? = null,
    /**
     * # The default tenant's shard size when shuffle-sharding is enabled in the ruler.
     * # When this setting is specified in the per-tenant overrides, a value of 0
     * # disables shuffle sharding for the tenant.
     * # CLI flag: -ruler.tenant-shard-size
     * [ruler_tenant_shard_size: <int> | default = 0]
     */
    val rulerTenantShardSize: Int? = null,
    /**
     * # Enable WAL replay on ruler startup. Disabling this can reduce memory usage on
     * # startup at the cost of not recovering in-memory WAL metrics on restart.
     * # CLI flag: -ruler.enable-wal-replay
     * [ruler_enable_wal_replay: <boolean> | default = true]
     */
    val rulerEnableWALReplay: Boolean? = null,
    /**
     * # Disable recording rules remote-write.
     * [ruler_remote_write_disabled: <boolean>]
     */
    val rulerRemoteWriteDisabled: Boolean? = null,
    /**
     * # Deprecated: Use 'ruler_remote_write_config' instead. The URL of the endpoint
     * # to send samples to.
     * [ruler_remote_write_url: <string> | default = ""]
     */
    val rulerRemoteWriteURL: String? = null,
    /**
     * # Deprecated: Use 'ruler_remote_write_config' instead. Timeout for requests to
     * # the remote write endpoint.
     * [ruler_remote_write_timeout: <duration>]
     */
    val rulerRemoteWriteTimeout: Duration? = null,
    /**
     * # Deprecated: Use 'ruler_remote_write_config' instead. Custom HTTP headers to be
     * # sent along with each remote write request. Be aware that headers that are set
     * # by Loki itself can't be overwritten.
     * [ruler_remote_write_headers: <headers>]
     */
    val rulerRemoteWriteHeaders: List<Header>? = null,
    /**
     * # Configures global and per-tenant limits for remote write clients. A map with
     * # remote client id as key.
     * [ruler_remote_write_config: <map of string to RemoteWriteConfig>] - why is this not defined...?
     */
    val ruleRemoteWriteConfig: Map<String, Any>? = null,
    /**
     * # Timeout for a remote rule evaluation. Defaults to the value of
     * # 'querier.query-timeout'.
     * [ruler_remote_evaluation_timeout: <duration>]
     */
    val rulerRemoteEvaluationTimeout: Duration? = null,
    /**
     * # Maximum size (in bytes) of the allowable response size from a remote rule
     * # evaluation. Set to 0 to allow any response size (default).
     * [ruler_remote_evaluation_max_response_size: <int>]
     */
    val rulerRemoteEvaluationMaxResponseSize: Int? = null,
    /**
     * # Deletion mode. Can be one of 'disabled', 'filter-only', or
     * # 'filter-and-delete'. When set to 'filter-only' or 'filter-and-delete', and if
     * # retention_enabled is true, then the log entry deletion API endpoints are
     * # available.
     * # CLI flag: -compactor.deletion-mode
     * [deletion_mode: <string> | default = "filter-and-delete"]
     */
    val deletionMode: String? = null,
    /**
     * # Retention period to apply to stored data, only applies if retention_enabled is
     * # true in the compactor config. As of version 2.8.0, a zero value of 0 or 0s
     * # disables retention. In previous releases, Loki did not properly honor a zero
     * # value to disable retention and a really large value should be used instead.
     * # CLI flag: -store.retention
     * [retention_period: <duration> | default = 0s]
     */
    val retentionPeriod: Boolean? = null,
    /**
     * # Per-stream retention to apply, if the retention is enabled on the compactor
     * # side.
     * # Example:
     * #  retention_stream:
     * #  - selector: '{namespace="dev"}'
     * #  priority: 1
     * #  period: 24h
     * # - selector: '{container="nginx"}'
     * #  priority: 1
     * #  period: 744h
     * # Selector is a Prometheus labels matchers that will apply the 'period'
     * # retention only if the stream is matching. In case multiple streams are
     * # matching, the highest priority will be picked. If no rule is matched the
     * # 'retention_period' is used.
     * [retention_stream: <list of StreamRetentions>] - why is this not defined...?
     */
    val retentionStream: List<Any>? = null,
    /**
     * # Feature renamed to 'runtime configuration', flag deprecated in favor of
     * # -runtime-config.file (runtime_config.file in YAML).
     * # CLI flag: -limits.per-user-override-config
     * [per_tenant_override_config: <string> | default = ""]
     */
    val perTenantOverrideConfig: String? = null,
    /**
     * # Feature renamed to 'runtime configuration'; flag deprecated in favor of
     * # -runtime-config.reload-period (runtime_config.period in YAML).
     * # CLI flag: -limits.per-user-override-period
     * [per_tenant_override_period: <duration> | default = 10s]
     */
    val perTenantOverridePeriod: Duration? = null,
    /**
     * # Define streams sharding behavior.
     */
    val shardStreams: ShardStreams? = null,
    /**
     *
     * [blocked_queries: <blocked_query...>] // wtf? someone got lazy
     */
    val blockedQueries: List<Any>? = null,

    /**
     *
     * # Define a list of required selector labels.
     * [required_labels: <list of strings>]
     */
    val requiredLabels: List<String>? = null,
    /**
     * # Minimum number of label matchers a query should contain.
     * [minimum_labels_number: <int>]
     */
    val minimumLabelsNumber: Int? = null,
    /**
     * # The shard size defines how many index gateways should be used by a tenant for
     * # querying. If the global shard factor is 0, the global shard factor is set to
     * # the deprecated -replication-factor for backwards compatibility reasons.
     * # CLI flag: -index-gateway.shard-size
     * [index_gateway_shard_size: <int> | default = 0]
     */
    val indexGatewayShardSize: Int? = null,
    /**
     * # Allow user to send structured metadata in push payload.
     * # CLI flag: -validation.allow-structured-metadata
     * [allow_structured_metadata: <boolean> | default = true]
     */
    val allowStructuredMetadata: Boolean? = null,
    /**
     * # Maximum size accepted for structured metadata per log line.
     * # CLI flag: -limits.max-structured-metadata-size
     * [max_structured_metadata_size: <int> | default = 64KB]
     */
    val maxStructuredMetadataSize: Int? = null,
    /**
     * # Maximum number of structured metadata entries per log line.
     * # CLI flag: -limits.max-structured-metadata-entries-count
     * [max_structured_metadata_entries_count: <int> | default = 128]
     */
    val maxStructuredMetadataEntriesCount: Int? = null,
    /**
     * # OTLP log ingestion configurations
     */
    val otlpConfig: OTLPConfig? = null,
    /**
     * # Block ingestion for policy until the configured date. The policy '*' is the
     * # global policy, which is applied to all streams not matching a policy and can
     * # be overridden by other policies. The time should be in RFC3339 format. The
     * # policy is based on the policy_stream_mapping configuration.
     * [block_ingestion_policy_until: <map of string to Time>]
     */
    val blockIngestionPolicyUntil: Map<String, LocalDateTime>? = null,
    /**
     * # Block ingestion until the configured date. The time should be in RFC3339
     * # format.
     * # CLI flag: -limits.block-ingestion-until
     * [block_ingestion_until: <time> | default = 0]
     */
    val blockIngestionUntil: LocalDateTime? = null,
    /**
     * # HTTP status code to return when ingestion is blocked. If 200, the ingestion
     * # will be blocked without returning an error to the client. By Default, a custom
     * # status code (260) is returned to the client along with an error message.
     * # CLI flag: -limits.block-ingestion-status-code
     * [block_ingestion_status_code: <int> | default = 260]
     */
    val blockIngestionStatusCode: Int? = null,
    /**
     * # List of labels that must be present in the stream. If any of the labels are
     * # missing, the stream will be discarded. This flag configures it globally for
     * # all tenants. Experimental.
     * # CLI flag: -validation.enforced-labels
     * [enforced_labels: <list of strings> | default = []]
     */
    val enforcedLabels: List<String>? = null,
    /**
     * # Map of policies to enforced labels. The policy '*' is the global policy, which
     * # is applied to all streams and can be extended by other policies. Example:
     * #  policy_enforced_labels:
     * #   policy1:
     * #     - label1
     * #     - label2
     * #   policy2:
     * #     - label3
     * #     - label4
     * #   '*':
     * #     - label5
     * [policy_enforced_labels: <map of string to list of strings>]
     */
    val policyEnforcedLabels: List<String>? = null,
    /**
     *
     * # Map of policies to stream selectors with a priority. Experimental.  Example:
     * #  policy_stream_mapping:
     * #   finance:
     * #     - selector: '{namespace="prod", container="billing"}'
     * #       priority: 2
     * #   ops:
     * #     - selector: '{namespace="prod", container="ops"}'
     * #       priority: 1
     * #   staging:
     * #     - selector: '{namespace="staging"}'
     * #       priority: 1
     * [policy_stream_mapping: <map of string to list of PriorityStreams>] // undefined
     */
    val policyStreamMapping: Map<String, Any>? = null,
    /**
     *
     * # The number of partitions a tenant's data should be sharded to when using kafka
     * # ingestion. Tenants are sharded across partitions using shuffle-sharding. 0
     * # disables shuffle sharding and tenant is sharded across all partitions.
     * # CLI flag: -limits.ingestion-partition-tenant-shard-size
     * [ingestion_partitions_tenant_shard_size: <int> | default = 0]
     */
    val ingestionPartitionsTenantShardSize: Int? = null,
    /**
     * # List of LogQL vector and range aggregations that should be sharded.
     * [shard_aggregations: <list of strings>]
     */
    val shardAggregations: List<String>? = null,
    /**
     * # Enable metric aggregation. When enabled, pushed streams will be sampled for
     * # bytes and count, and these metric will be written back into Loki as a special
     * # __aggregated_metric__ stream, which can be queried for faster histogram
     * # queries.
     * # CLI flag: -limits.metric-aggregation-enabled
     * [metric_aggregation_enabled: <boolean> | default = false]
     */
    val metricAggregationEnabled: Boolean? = null,
    /**
     * # S3 server-side encryption type. Required to enable server-side encryption
     * # overrides for a specific tenant. If not set, the default S3 client settings
     * # are used.
     * [s3_sse_type: <string> | default = ""]
     */
    val s3SseType: String? = null,
    /**
     * # S3 server-side encryption KMS Key ID. Ignored if the SSE type override is not
     * # set.
     * [s3_sse_kms_key_id: <string> | default = ""]
     */
    val s3SseKmsKeyId: String? = null,
    /**
     * # S3 server-side encryption KMS encryption context. If unset and the key ID
     * # override is set, the encryption context will not be provided to S3. Ignored if
     * # the SSE type override is not set.
     * [s3_sse_kms_encryption_context: <string> | default = ""]
     */
    val s3SseKmsEncryptionContext: String? = null
)