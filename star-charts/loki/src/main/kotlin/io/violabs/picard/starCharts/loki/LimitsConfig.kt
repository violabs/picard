package io.violabs.picard.starCharts.loki

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
     *
     * # Per-user ingestion rate limit in sample size per second. Sample size includes
     * # size of the logs line and the size of structured metadata labels. Units in MB.
     * # CLI flag: -distributor.ingestion-rate-limit-mb
     * [ingestion_rate_mb: <float> | default = 4]
     *
     * # Per-user allowed ingestion burst size (in sample size). Units in MB. The burst
     * # size refers to the per-distributor local rate limiter even in the case of the
     * # 'global' strategy, and should be set at least to the maximum logs size
     * # expected in a single push request.
     * # CLI flag: -distributor.ingestion-burst-size-mb
     * [ingestion_burst_size_mb: <float> | default = 6]
     *
     * # Maximum length accepted for label names.
     * # CLI flag: -validation.max-length-label-name
     * [max_label_name_length: <int> | default = 1024]
     *
     * # Maximum length accepted for label value. This setting also applies to the
     * # metric name.
     * # CLI flag: -validation.max-length-label-value
     * [max_label_value_length: <int> | default = 2048]
     *
     * # Maximum number of label names per series.
     * # CLI flag: -validation.max-label-names-per-series
     * [max_label_names_per_series: <int> | default = 15]
     *
     * # Whether or not old samples will be rejected.
     * # CLI flag: -validation.reject-old-samples
     * [reject_old_samples: <boolean> | default = true]
     *
     * # Maximum accepted sample age before rejecting.
     * # CLI flag: -validation.reject-old-samples.max-age
     * [reject_old_samples_max_age: <duration> | default = 1w]
     *
     * # Duration which table will be created/deleted before/after it's needed; we
     * # won't accept sample from before this time.
     * # CLI flag: -validation.create-grace-period
     * [creation_grace_period: <duration> | default = 10m]
     *
     * # Maximum line size on ingestion path. Example: 256kb. Any log line exceeding
     * # this limit will be discarded unless `distributor.max-line-size-truncate` is
     * # set which in case it is truncated instead of discarding it completely. There
     * # is no limit when unset or set to 0.
     * # CLI flag: -distributor.max-line-size
     * [max_line_size: <int> | default = 256KB]
     *
     * # Whether to truncate lines that exceed max_line_size.
     * # CLI flag: -distributor.max-line-size-truncate
     * [max_line_size_truncate: <boolean> | default = false]
     *
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
     *
     * # Simulated latency to add to push requests. Used for testing. Set to 0s to
     * # disable.
     * # CLI flag: -limits.simulated-push-latency
     * [simulated_push_latency: <duration> | default = 0s]
     *
     * # Enable experimental support for running multiple query variants over the same
     * # underlying data. For example, running both a rate() and count_over_time()
     * # query over the same range selector.
     * # CLI flag: -limits.enable-multi-variant-queries
     * [enable_multi_variant_queries: <boolean> | default = false]
     *
     * # Experimental: Detect fields from stream labels, structured metadata, or
     * # json/logfmt formatted log line and put them into structured metadata of the
     * # log entry.
     * discover_generic_fields:
     *   [fields: <map of string to list of strings>]
     *
     * # If no service_name label exists, Loki maps a single label from the configured
     * # list to service_name. If none of the configured labels exist in the stream,
     * # label is set to unknown_service. Empty list disables setting the label.
     * # CLI flag: -validation.discover-service-name
     * [discover_service_name: <list of strings> | default = [service app application app_name name app_kubernetes_io_name container container_name k8s_container_name component workload job k8s_job_name]]
     *
     * # Discover and add log levels during ingestion, if not present already. Levels
     * # would be added to Structured Metadata with name
     * # level/LEVEL/Level/Severity/severity/SEVERITY/lvl/LVL/Lvl (case-sensitive) and
     * # one of the values from 'trace', 'debug', 'info', 'warn', 'error', 'critical',
     * # 'fatal' (case insensitive).
     * # CLI flag: -validation.discover-log-levels
     * [discover_log_levels: <boolean> | default = true]
     *
     * # Field name to use for log levels. If not set, log level would be detected
     * # based on pre-defined labels as mentioned above.
     * # CLI flag: -validation.log-level-fields
     * [log_level_fields: <list of strings> | default = [level LEVEL Level Severity severity SEVERITY lvl LVL Lvl severity_text Severity_Text SEVERITY_TEXT]]
     *
     * # Maximum depth to search for log level fields in JSON logs. A value of 0 or
     * # less means unlimited depth. Default is 2 which searches the first 2 levels of
     * # the JSON object.
     * # CLI flag: -validation.log-level-from-json-max-depth
     * [log_level_from_json_max_depth: <int> | default = 2]
     *
     * # When true an ingester takes into account only the streams that it owns
     * # according to the ring while applying the stream limit.
     * # CLI flag: -ingester.use-owned-stream-count
     * [use_owned_stream_count: <boolean> | default = false]
     *
     * # Maximum number of active streams per user, per ingester. 0 to disable.
     * # CLI flag: -ingester.max-streams-per-user
     * [max_streams_per_user: <int> | default = 0]
     *
     * # Maximum number of active streams per user, across the cluster. 0 to disable.
     * # When the global limit is enabled, each ingester is configured with a dynamic
     * # local limit based on the replication factor and the current number of healthy
     * # ingesters, and is kept updated whenever the number of ingesters change.
     * # CLI flag: -ingester.max-global-streams-per-user
     * [max_global_streams_per_user: <int> | default = 5000]
     *
     * # Deprecated. When true, out-of-order writes are accepted.
     * # CLI flag: -ingester.unordered-writes
     * [unordered_writes: <boolean> | default = true]
     *
     * # Maximum byte rate per second per stream, also expressible in human readable
     * # forms (1MB, 256KB, etc).
     * # CLI flag: -ingester.per-stream-rate-limit
     * [per_stream_rate_limit: <int> | default = 3MB]
     *
     * # Maximum burst bytes per stream, also expressible in human readable forms (1MB,
     * # 256KB, etc). This is how far above the rate limit a stream can 'burst' before
     * # the stream is limited.
     * # CLI flag: -ingester.per-stream-rate-limit-burst
     * [per_stream_rate_limit_burst: <int> | default = 15MB]
     *
     * # Maximum number of chunks that can be fetched in a single query.
     * # CLI flag: -store.query-chunk-limit
     * [max_chunks_per_query: <int> | default = 2000000]
     *
     * # Limit the maximum of unique series that is returned by a metric query. When
     * # the limit is reached an error is returned.
     * # CLI flag: -querier.max-query-series
     * [max_query_series: <int> | default = 500]
     *
     * # Limit how far back in time series data and metadata can be queried, up until
     * # lookback duration ago. This limit is enforced in the query frontend, the
     * # querier and the ruler. If the requested time range is outside the allowed
     * # range, the request will not fail, but will be modified to only query data
     * # within the allowed time range. The default value of 0 does not set a limit.
     * # CLI flag: -querier.max-query-lookback
     * [max_query_lookback: <duration> | default = 0s]
     *
     * # The limit to length of chunk store queries. 0 to disable.
     * # CLI flag: -store.max-query-length
     * [max_query_length: <duration> | default = 30d1h]
     *
     * # Limit the length of the [range] inside a range query. Default is 0 or
     * # unlimited
     * # CLI flag: -querier.max-query-range
     * [max_query_range: <duration> | default = 0s]
     *
     * # Maximum number of queries that will be scheduled in parallel by the frontend.
     * # CLI flag: -querier.max-query-parallelism
     * [max_query_parallelism: <int> | default = 32]
     *
     * # Maximum number of queries will be scheduled in parallel by the frontend for
     * # TSDB schemas.
     * # CLI flag: -querier.tsdb-max-query-parallelism
     * [tsdb_max_query_parallelism: <int> | default = 128]
     *
     * # Target maximum number of bytes assigned to a single sharded query. Also
     * # expressible in human readable forms (1GB, etc). Note: This is a _target_ and
     * # not an absolute limit. The actual limit can be higher, but the query planner
     * # will try to build shards up to this limit.
     * # CLI flag: -querier.tsdb-max-bytes-per-shard
     * [tsdb_max_bytes_per_shard: <int> | default = 600MB]
     *
     * # sharding strategy to use in query planning. Suggested to use bounded once all
     * # nodes can recognize it.
     * # CLI flag: -limits.tsdb-sharding-strategy
     * [tsdb_sharding_strategy: <string> | default = "power_of_two"]
     *
     * # Precompute chunks for TSDB queries. This can improve query performance at the
     * # cost of increased memory usage by computing chunks once during planning,
     * # reducing index calls.
     * # CLI flag: -querier.tsdb-precompute-chunks
     * [tsdb_precompute_chunks: <boolean> | default = false]
     *
     * # Cardinality limit for index queries.
     * # CLI flag: -store.cardinality-limit
     * [cardinality_limit: <int> | default = 100000]
     *
     * # Maximum number of stream matchers per query.
     * # CLI flag: -querier.max-streams-matcher-per-query
     * [max_streams_matchers_per_query: <int> | default = 1000]
     *
     * # Maximum number of concurrent tail requests.
     * # CLI flag: -querier.max-concurrent-tail-requests
     * [max_concurrent_tail_requests: <int> | default = 10]
     *
     * # Maximum number of log entries that will be returned for a query.
     * # CLI flag: -validation.max-entries-limit
     * [max_entries_limit_per_query: <int> | default = 5000]
     *
     * # Most recent allowed cacheable result per-tenant, to prevent caching very
     * # recent results that might still be in flux.
     * # CLI flag: -frontend.max-cache-freshness
     * [max_cache_freshness_per_query: <duration> | default = 10m]
     *
     * # Do not cache metadata request if the end time is within the
     * # frontend.max-metadata-cache-freshness window. Set this to 0 to apply no such
     * # limits. Defaults to 24h.
     * # CLI flag: -frontend.max-metadata-cache-freshness
     * [max_metadata_cache_freshness: <duration> | default = 1d]
     *
     * # Do not cache requests with an end time that falls within Now minus this
     * # duration. 0 disables this feature (default).
     * # CLI flag: -frontend.max-stats-cache-freshness
     * [max_stats_cache_freshness: <duration> | default = 10m]
     *
     * # Maximum number of queriers that can handle requests for a single tenant. If
     * # set to 0 or value higher than number of available queriers, *all* queriers
     * # will handle requests for the tenant. Each frontend (or query-scheduler, if
     * # used) will select the same set of queriers for the same tenant (given that all
     * # queriers are connected to all frontends / query-schedulers). This option only
     * # works with queriers connecting to the query-frontend / query-scheduler, not
     * # when using downstream URL.
     * # CLI flag: -frontend.max-queriers-per-tenant
     * [max_queriers_per_tenant: <int> | default = 0]
     *
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
     *
     * # Number of days of index to be kept always downloaded for queries. Applies only
     * # to per user index in boltdb-shipper index store. 0 to disable.
     * # CLI flag: -store.query-ready-index-num-days
     * [query_ready_index_num_days: <int> | default = 0]
     *
     * # Timeout when querying backends (ingesters or storage) during the execution of
     * # a query request. When a specific per-tenant timeout is used, the global
     * # timeout is ignored.
     * # CLI flag: -querier.query-timeout
     * [query_timeout: <duration> | default = 1m]
     *
     * # Split queries by a time interval and execute in parallel. The value 0 disables
     * # splitting by time. This also determines how cache keys are chosen when result
     * # caching is enabled.
     * # CLI flag: -querier.split-queries-by-interval
     * [split_queries_by_interval: <duration> | default = 1h]
     *
     * # Split metadata queries by a time interval and execute in parallel. The value 0
     * # disables splitting metadata queries by time. This also determines how cache
     * # keys are chosen when label/series result caching is enabled.
     * # CLI flag: -querier.split-metadata-queries-by-interval
     * [split_metadata_queries_by_interval: <duration> | default = 1d]
     *
     * # Experimental. Split interval to use for the portion of metadata request that
     * # falls within `recent_metadata_query_window`. Rest of the request which is
     * # outside the window still uses `split_metadata_queries_by_interval`. If set to
     * # 0, the entire request defaults to using a split interval of
     * # `split_metadata_queries_by_interval.`.
     * # CLI flag: -experimental.querier.split-recent-metadata-queries-by-interval
     * [split_recent_metadata_queries_by_interval: <duration> | default = 1h]
     *
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
     *
     * # Split instant metric queries by a time interval and execute in parallel. The
     * # value 0 disables splitting instant metric queries by time. This also
     * # determines how cache keys are chosen when instant metric query result caching
     * # is enabled.
     * # CLI flag: -querier.split-instant-metric-queries-by-interval
     * [split_instant_metric_queries_by_interval: <duration> | default = 1h]
     *
     * # Interval to use for time-based splitting when a request is within the
     * # `query_ingesters_within` window; defaults to `split-queries-by-interval` by
     * # setting to 0.
     * # CLI flag: -querier.split-ingester-queries-by-interval
     * [split_ingester_queries_by_interval: <duration> | default = 0s]
     *
     * # Limit queries that can be sharded. Queries within the time range of now and
     * # now minus this sharding lookback are not sharded. The default value of 0s
     * # disables the lookback, causing sharding of all queries at all times.
     * # CLI flag: -frontend.min-sharding-lookback
     * [min_sharding_lookback: <duration> | default = 0s]
     *
     * # Max number of bytes a query can fetch. Enforced in log and metric queries only
     * # when TSDB is used. This limit is not enforced on log queries without filters.
     * # The default value of 0 disables this limit.
     * # CLI flag: -frontend.max-query-bytes-read
     * [max_query_bytes_read: <int> | default = 0B]
     *
     * # Max number of bytes a query can fetch after splitting and sharding. Enforced
     * # in log and metric queries only when TSDB is used. This limit is not enforced
     * # on log queries without filters. The default value of 0 disables this limit.
     * # CLI flag: -frontend.max-querier-bytes-read
     * [max_querier_bytes_read: <int> | default = 150GB]
     *
     * # Enable log-volume endpoints.
     * # CLI flag: -limits.volume-enabled
     * [volume_enabled: <boolean> | default = true]
     *
     * # The maximum number of aggregated series in a log-volume response
     * # CLI flag: -limits.volume-max-series
     * [volume_max_series: <int> | default = 1000]
     *
     * # Maximum number of rules per rule group per-tenant. 0 to disable.
     * # CLI flag: -ruler.max-rules-per-rule-group
     * [ruler_max_rules_per_rule_group: <int> | default = 0]
     *
     * # Maximum number of rule groups per-tenant. 0 to disable.
     * # CLI flag: -ruler.max-rule-groups-per-tenant
     * [ruler_max_rule_groups_per_tenant: <int> | default = 0]
     *
     * # The default tenant's shard size when shuffle-sharding is enabled in the ruler.
     * # When this setting is specified in the per-tenant overrides, a value of 0
     * # disables shuffle sharding for the tenant.
     * # CLI flag: -ruler.tenant-shard-size
     * [ruler_tenant_shard_size: <int> | default = 0]
     *
     * # Enable WAL replay on ruler startup. Disabling this can reduce memory usage on
     * # startup at the cost of not recovering in-memory WAL metrics on restart.
     * # CLI flag: -ruler.enable-wal-replay
     * [ruler_enable_wal_replay: <boolean> | default = true]
     *
     * # Disable recording rules remote-write.
     * [ruler_remote_write_disabled: <boolean>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. The URL of the endpoint
     * # to send samples to.
     * [ruler_remote_write_url: <string> | default = ""]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Timeout for requests to
     * # the remote write endpoint.
     * [ruler_remote_write_timeout: <duration>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Custom HTTP headers to be
     * # sent along with each remote write request. Be aware that headers that are set
     * # by Loki itself can't be overwritten.
     * [ruler_remote_write_headers: <headers>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. List of remote write
     * # relabel configurations.
     * [ruler_remote_write_relabel_configs: <relabel_config...>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Number of samples to
     * # buffer per shard before we block reading of more samples from the WAL. It is
     * # recommended to have enough capacity in each shard to buffer several requests
     * # to keep throughput up while processing occasional slow remote requests.
     * [ruler_remote_write_queue_capacity: <int>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Minimum number of shards,
     * # i.e. amount of concurrency.
     * [ruler_remote_write_queue_min_shards: <int>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Maximum number of shards,
     * # i.e. amount of concurrency.
     * [ruler_remote_write_queue_max_shards: <int>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Maximum number of samples
     * # per send.
     * [ruler_remote_write_queue_max_samples_per_send: <int>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Maximum time a sample
     * # will wait in buffer.
     * [ruler_remote_write_queue_batch_send_deadline: <duration>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Initial retry delay. Gets
     * # doubled for every retry.
     * [ruler_remote_write_queue_min_backoff: <duration>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Maximum retry delay.
     * [ruler_remote_write_queue_max_backoff: <duration>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Retry upon receiving a
     * # 429 status code from the remote-write storage. This is experimental and might
     * # change in the future.
     * [ruler_remote_write_queue_retry_on_ratelimit: <boolean>]
     *
     * # Deprecated: Use 'ruler_remote_write_config' instead. Configures AWS's
     * # Signature Verification 4 signing process to sign every remote write request.
     * ruler_remote_write_sigv4_config:
     *   [region: <string> | default = ""]
     *
     *   [access_key: <string> | default = ""]
     *
     *   [secret_key: <string> | default = ""]
     *
     *   [profile: <string> | default = ""]
     *
     *   [role_arn: <string> | default = ""]
     *
     * # Configures global and per-tenant limits for remote write clients. A map with
     * # remote client id as key.
     * [ruler_remote_write_config: <map of string to RemoteWriteConfig>]
     *
     * # Timeout for a remote rule evaluation. Defaults to the value of
     * # 'querier.query-timeout'.
     * [ruler_remote_evaluation_timeout: <duration>]
     *
     * # Maximum size (in bytes) of the allowable response size from a remote rule
     * # evaluation. Set to 0 to allow any response size (default).
     * [ruler_remote_evaluation_max_response_size: <int>]
     *
     * # Deletion mode. Can be one of 'disabled', 'filter-only', or
     * # 'filter-and-delete'. When set to 'filter-only' or 'filter-and-delete', and if
     * # retention_enabled is true, then the log entry deletion API endpoints are
     * # available.
     * # CLI flag: -compactor.deletion-mode
     * [deletion_mode: <string> | default = "filter-and-delete"]
     *
     * # Retention period to apply to stored data, only applies if retention_enabled is
     * # true in the compactor config. As of version 2.8.0, a zero value of 0 or 0s
     * # disables retention. In previous releases, Loki did not properly honor a zero
     * # value to disable retention and a really large value should be used instead.
     * # CLI flag: -store.retention
     * [retention_period: <duration> | default = 0s]
     *
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
     * [retention_stream: <list of StreamRetentions>]
     *
     * # Feature renamed to 'runtime configuration', flag deprecated in favor of
     * # -runtime-config.file (runtime_config.file in YAML).
     * # CLI flag: -limits.per-user-override-config
     * [per_tenant_override_config: <string> | default = ""]
     *
     * # Feature renamed to 'runtime configuration'; flag deprecated in favor of
     * # -runtime-config.reload-period (runtime_config.period in YAML).
     * # CLI flag: -limits.per-user-override-period
     * [per_tenant_override_period: <duration> | default = 10s]
     *
     * # Deprecated: Use deletion_mode per tenant configuration instead.
     * [allow_deletes: <boolean>]
     *
     * # Define streams sharding behavior.
     * shard_streams:
     *   # Automatically shard streams to keep them under the per-stream rate limit.
     *   # Sharding is dictated by the desired rate.
     *   # CLI flag: -shard-streams.enabled
     *   [enabled: <boolean> | default = true]
     *
     *   # Automatically shard streams by adding a __time_shard__ label, with values
     *   # calculated from the log timestamps divided by MaxChunkAge/2. This allows the
     *   # out-of-order ingestion of very old logs. If both flags are enabled,
     *   # time-based sharding will happen before rate-based sharding.
     *   # CLI flag: -shard-streams.time-sharding-enabled
     *   [time_sharding_enabled: <boolean> | default = false]
     *
     *   # Logs with timestamps that are newer than this value will not be
     *   # time-sharded.
     *   # CLI flag: -shard-streams.time-sharding-ignore-recent
     *   [time_sharding_ignore_recent: <duration> | default = 40m]
     *
     *   # Whether to log sharding streams behavior or not. Not recommended for
     *   # production environments.
     *   # CLI flag: -shard-streams.logging-enabled
     *   [logging_enabled: <boolean> | default = false]
     *
     *   # Threshold used to cut a new shard. Default (1536KB) means if a rate is above
     *   # 1536KB/s, it will be sharded into two streams.
     *   # CLI flag: -shard-streams.desired-rate
     *   [desired_rate: <int> | default = 1536KB]
     *
     * [blocked_queries: <blocked_query...>]
     *
     * # Define a list of required selector labels.
     * [required_labels: <list of strings>]
     *
     * # Minimum number of label matchers a query should contain.
     * [minimum_labels_number: <int>]
     *
     * # The shard size defines how many index gateways should be used by a tenant for
     * # querying. If the global shard factor is 0, the global shard factor is set to
     * # the deprecated -replication-factor for backwards compatibility reasons.
     * # CLI flag: -index-gateway.shard-size
     * [index_gateway_shard_size: <int> | default = 0]
     *
     * # Experimental. Whether to use the bloom gateway component in the read path to
     * # filter chunks.
     * # CLI flag: -bloom-gateway.enable-filtering
     * [bloom_gateway_enable_filtering: <boolean> | default = false]
     *
     * # Experimental. Maximum number of builders to use when building blooms. 0 allows
     * # unlimited builders.
     * # CLI flag: -bloom-build.max-builders
     * [bloom_build_max_builders: <int> | default = 0]
     *
     * # Experimental. Maximum number of retries for a failed task. If a task fails
     * # more than this number of times, it is considered failed and will not be
     * # retried. A value of 0 disables this limit.
     * # CLI flag: -bloom-build.task-max-retries
     * [bloom_build_task_max_retries: <int> | default = 3]
     *
     * # Experimental. Timeout for a builder to finish a task. If a builder does not
     * # respond within this time, it is considered failed and the task will be
     * # requeued. 0 disables the timeout.
     * # CLI flag: -bloom-build.builder-response-timeout
     * [bloom_build_builder_response_timeout: <duration> | default = 0s]
     *
     * # Experimental. Whether to create blooms for the tenant.
     * # CLI flag: -bloom-build.enable
     * [bloom_creation_enabled: <boolean> | default = false]
     *
     * # Experimental. Bloom planning strategy to use in bloom creation. Can be one of:
     * # 'split_keyspace_by_factor', 'split_by_series_chunks_size'
     * # CLI flag: -bloom-build.planning-strategy
     * [bloom_planning_strategy: <string> | default = "split_keyspace_by_factor"]
     *
     * # Experimental. Only if `bloom-build.planning-strategy` is 'split'. Number of
     * # splits to create for the series keyspace when building blooms. The series
     * # keyspace is split into this many parts to parallelize bloom creation.
     * # CLI flag: -bloom-build.split-keyspace-by
     * [bloom_split_series_keyspace_by: <int> | default = 256]
     *
     * # Experimental. Target chunk size in bytes for bloom tasks. Default is 20GB.
     * # CLI flag: -bloom-build.split-target-series-chunk-size
     * [bloom_task_target_series_chunk_size: <int> | default = 20GB]
     *
     * # Experimental. Compression algorithm for bloom block pages.
     * # CLI flag: -bloom-build.block-encoding
     * [bloom_block_encoding: <string> | default = "none"]
     *
     * # Experimental. Prefetch blocks on bloom gateways as soon as they are built.
     * # CLI flag: -bloom-build.prefetch-blocks
     * [bloom_prefetch_blocks: <boolean> | default = false]
     *
     * # Experimental. The maximum bloom block size. A value of 0 sets an unlimited
     * # size. Default is 200MB. The actual block size might exceed this limit since
     * # blooms will be added to blocks until the block exceeds the maximum block size.
     * # CLI flag: -bloom-build.max-block-size
     * [bloom_max_block_size: <int> | default = 200MB]
     *
     * # Experimental. The maximum bloom size per log stream. A log stream whose
     * # generated bloom filter exceeds this size will be discarded. A value of 0 sets
     * # an unlimited size. Default is 128MB.
     * # CLI flag: -bloom-build.max-bloom-size
     * [bloom_max_bloom_size: <int> | default = 128MB]
     *
     * # Allow user to send structured metadata in push payload.
     * # CLI flag: -validation.allow-structured-metadata
     * [allow_structured_metadata: <boolean> | default = true]
     *
     * # Maximum size accepted for structured metadata per log line.
     * # CLI flag: -limits.max-structured-metadata-size
     * [max_structured_metadata_size: <int> | default = 64KB]
     *
     * # Maximum number of structured metadata entries per log line.
     * # CLI flag: -limits.max-structured-metadata-entries-count
     * [max_structured_metadata_entries_count: <int> | default = 128]
     *
     * # OTLP log ingestion configurations
     * otlp_config:
     *   # Configuration for resource attributes to store them as index labels or
     *   # Structured Metadata or drop them altogether
     *   resource_attributes:
     *     # Configure whether to ignore the default list of resource attributes set in
     *     # 'distributor.otlp.default_resource_attributes_as_index_labels' to be
     *     # stored as index labels and only use the given resource attributes config
     *     [ignore_defaults: <boolean> | default = false]
     *
     *     [attributes_config: <list of attributes_configs>]
     *
     *   # Configuration for scope attributes to store them as Structured Metadata or
     *   # drop them altogether
     *   [scope_attributes: <list of attributes_configs>]
     *
     *   # Configuration for log attributes to store them as index labels or Structured
     *   # Metadata or drop them altogether
     *   [log_attributes: <list of attributes_configs>]
     *
     *   # When true, the severity_text field from log records will be stored as an
     *   # index label. It is recommended not to use this option unless absolutely
     *   # necessary
     *   [severity_text_as_label: <boolean> | default = false]
     *
     * # Block ingestion for policy until the configured date. The policy '*' is the
     * # global policy, which is applied to all streams not matching a policy and can
     * # be overridden by other policies. The time should be in RFC3339 format. The
     * # policy is based on the policy_stream_mapping configuration.
     * [block_ingestion_policy_until: <map of string to Time>]
     *
     * # Block ingestion until the configured date. The time should be in RFC3339
     * # format.
     * # CLI flag: -limits.block-ingestion-until
     * [block_ingestion_until: <time> | default = 0]
     *
     * # HTTP status code to return when ingestion is blocked. If 200, the ingestion
     * # will be blocked without returning an error to the client. By Default, a custom
     * # status code (260) is returned to the client along with an error message.
     * # CLI flag: -limits.block-ingestion-status-code
     * [block_ingestion_status_code: <int> | default = 260]
     *
     * # List of labels that must be present in the stream. If any of the labels are
     * # missing, the stream will be discarded. This flag configures it globally for
     * # all tenants. Experimental.
     * # CLI flag: -validation.enforced-labels
     * [enforced_labels: <list of strings> | default = []]
     *
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
     * [policy_stream_mapping: <map of string to list of PriorityStreams>]
     *
     * # The number of partitions a tenant's data should be sharded to when using kafka
     * # ingestion. Tenants are sharded across partitions using shuffle-sharding. 0
     * # disables shuffle sharding and tenant is sharded across all partitions.
     * # CLI flag: -limits.ingestion-partition-tenant-shard-size
     * [ingestion_partitions_tenant_shard_size: <int> | default = 0]
     *
     * # List of LogQL vector and range aggregations that should be sharded.
     * [shard_aggregations: <list of strings>]
     *
     * # Enable metric aggregation. When enabled, pushed streams will be sampled for
     * # bytes and count, and these metric will be written back into Loki as a special
     * # __aggregated_metric__ stream, which can be queried for faster histogram
     * # queries.
     * # CLI flag: -limits.metric-aggregation-enabled
     * [metric_aggregation_enabled: <boolean> | default = false]
     *
     * # S3 server-side encryption type. Required to enable server-side encryption
     * # overrides for a specific tenant. If not set, the default S3 client settings
     * # are used.
     * [s3_sse_type: <string> | default = ""]
     *
     * # S3 server-side encryption KMS Key ID. Ignored if the SSE type override is not
     * # set.
     * [s3_sse_kms_key_id: <string> | default = ""]
     *
     * # S3 server-side encryption KMS encryption context. If unset and the key ID
     * # override is set, the encryption context will not be provided to S3. Ignored if
     * # the SSE type override is not set.
     * [s3_sse_kms_encryption_context: <string> | default = ""]
     */
) {
}