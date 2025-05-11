package io.violabs.picard.starCharts.loki

class QueryRange(
    /**
     * # Mutate incoming queries to align their start and end with their step.
     * # CLI flag: -querier.align-querier-with-step
     * [align_queries_with_step: <boolean> | default = false]
     *
     * results_cache:
     *   # The cache_config block configures the cache backend for a specific Loki
     *   # component.
     *   # The CLI flags prefix for this block configuration is: frontend
     *   [cache: <cache_config>]
     *
     *   # Use compression in cache. The default is an empty value '', which disables
     *   # compression. Supported values are: 'snappy' and ''.
     *   # CLI flag: -frontend.compression
     *   [compression: <string> | default = ""]
     *
     * # Cache query results.
     * # CLI flag: -querier.cache-results
     * [cache_results: <boolean> | default = false]
     *
     * # Maximum number of retries for a single request; beyond this, the downstream
     * # error is returned.
     * # CLI flag: -querier.max-retries-per-request
     * [max_retries: <int> | default = 5]
     *
     * # Perform query parallelisations based on storage sharding configuration and
     * # query ASTs. This feature is supported only by the chunks storage engine.
     * # CLI flag: -querier.parallelise-shardable-queries
     * [parallelise_shardable_queries: <boolean> | default = true]
     *
     * # A comma-separated list of LogQL vector and range aggregations that should be
     * # sharded. Possible values 'quantile_over_time', 'last_over_time',
     * # 'first_over_time'.
     * # CLI flag: -querier.shard-aggregations
     * [shard_aggregations: <string> | default = ""]
     *
     * # Cache index stats query results.
     * # CLI flag: -querier.cache-index-stats-results
     * [cache_index_stats_results: <boolean> | default = true]
     *
     * # If a cache config is not specified and cache_index_stats_results is true, the
     * # config for the results cache is used.
     * index_stats_results_cache:
     *   # The cache_config block configures the cache backend for a specific Loki
     *   # component.
     *   # The CLI flags prefix for this block configuration is:
     *   # frontend.index-stats-results-cache
     *   [cache: <cache_config>]
     *
     *   # Use compression in cache. The default is an empty value '', which disables
     *   # compression. Supported values are: 'snappy' and ''.
     *   # CLI flag: -frontend.index-stats-results-cache.compression
     *   [compression: <string> | default = ""]
     *
     * # Cache volume query results.
     * # CLI flag: -querier.cache-volume-results
     * [cache_volume_results: <boolean> | default = true]
     *
     * # If a cache config is not specified and cache_volume_results is true, the
     * # config for the results cache is used.
     * volume_results_cache:
     *   # The cache_config block configures the cache backend for a specific Loki
     *   # component.
     *   # The CLI flags prefix for this block configuration is:
     *   # frontend.volume-results-cache
     *   [cache: <cache_config>]
     *
     *   # Use compression in cache. The default is an empty value '', which disables
     *   # compression. Supported values are: 'snappy' and ''.
     *   # CLI flag: -frontend.volume-results-cache.compression
     *   [compression: <string> | default = ""]
     *
     * # Cache instant metric query results.
     * # CLI flag: -querier.cache-instant-metric-results
     * [cache_instant_metric_results: <boolean> | default = false]
     *
     * # If a cache config is not specified and cache_instant_metric_results is true,
     * # the config for the results cache is used.
     * instant_metric_results_cache:
     *   # The cache_config block configures the cache backend for a specific Loki
     *   # component.
     *   # The CLI flags prefix for this block configuration is:
     *   # frontend.instant-metric-results-cache
     *   [cache: <cache_config>]
     *
     *   # Use compression in cache. The default is an empty value '', which disables
     *   # compression. Supported values are: 'snappy' and ''.
     *   # CLI flag: -frontend.instant-metric-results-cache.compression
     *   [compression: <string> | default = ""]
     *
     * # Whether to align the splits of instant metric query with splitByInterval and
     * # query's exec time. Useful when instant_metric_cache is enabled
     * # CLI flag: -querier.instant-metric-query-split-align
     * [instant_metric_query_split_align: <boolean> | default = false]
     *
     * # Cache series query results.
     * # CLI flag: -querier.cache-series-results
     * [cache_series_results: <boolean> | default = true]
     *
     * # If series_results_cache is not configured and cache_series_results is true,
     * # the config for the results cache is used.
     * series_results_cache:
     *   # The cache_config block configures the cache backend for a specific Loki
     *   # component.
     *   # The CLI flags prefix for this block configuration is:
     *   # frontend.series-results-cache
     *   [cache: <cache_config>]
     *
     *   # Use compression in cache. The default is an empty value '', which disables
     *   # compression. Supported values are: 'snappy' and ''.
     *   # CLI flag: -frontend.series-results-cache.compression
     *   [compression: <string> | default = ""]
     *
     * # Cache label query results.
     * # CLI flag: -querier.cache-label-results
     * [cache_label_results: <boolean> | default = true]
     *
     * # If label_results_cache is not configured and cache_label_results is true, the
     * # config for the results cache is used.
     * label_results_cache:
     *   # The cache_config block configures the cache backend for a specific Loki
     *   # component.
     *   # The CLI flags prefix for this block configuration is:
     *   # frontend.label-results-cache
     *   [cache: <cache_config>]
     *
     *   # Use compression in cache. The default is an empty value '', which disables
     *   # compression. Supported values are: 'snappy' and ''.
     *   # CLI flag: -frontend.label-results-cache.compression
     *   [compression: <string> | default = ""]
     */
) {
}