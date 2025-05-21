package io.violabs.picard.starCharts.loki

import io.violabs.picard.starCharts.loki.cacheConfig.CacheConfig

class QueryRange(
    /**
     * # Mutate incoming queries to align their start and end with their step.
     * # CLI flag: -querier.align-querier-with-step
     * [align_queries_with_step: <boolean> | default = false]
     */
    val alignQueriesWithStep: Boolean? = null,

    val resultsCache: ResultsCache? = null,
    /**
     * # Cache query results.
     * # CLI flag: -querier.cache-results
     * [cache_results: <boolean> | default = false]
     */
    val cacheResults: Boolean? = null,
    /**
     * # Maximum number of retries for a single request; beyond this, the downstream
     * # error is returned.
     * # CLI flag: -querier.max-retries-per-request
     * [max_retries: <int> | default = 5]
     */
    val maxRetries: Int? = null,
    /**
     * # Perform query parallelisations based on storage sharding configuration and
     * # query ASTs. This feature is supported only by the chunks storage engine.
     * # CLI flag: -querier.parallelise-shardable-queries
     * [parallelise_shardable_queries: <boolean> | default = true]
     */
    val paralleliseShardableQueries: Boolean? = null,
    /**
     * # A comma-separated list of LogQL vector and range aggregations that should be
     * # sharded. Possible values 'quantile_over_time', 'last_over_time',
     * # 'first_over_time'.
     * # CLI flag: -querier.shard-aggregations
     * [shard_aggregations: <string> | default = ""]
     */
    val shardAggregations: String? = null,
    /**
     * # Cache index stats query results.
     * # CLI flag: -querier.cache-index-stats-results
     * [cache_index_stats_results: <boolean> | default = true]
     */
    val cacheIndexStatsResults: Boolean? = null,

    val indexStatsResultsCache: IndexStatsResultsCache? = null,
    /**
     * # Cache volume query results.
     * # CLI flag: -querier.cache-volume-results
     * [cache_volume_results: <boolean> | default = true]
     */
    val cacheVolumeResults: Boolean? = null,

    val volumeResultsCache: VolumeResultsCache? = null,
    /**
     * # Cache instant metric query results.
     * # CLI flag: -querier.cache-instant-metric-results
     * [cache_instant_metric_results: <boolean> | default = false]
     */
    val cacheInstantMetricResults: Boolean? = null,

    val instantMetricResultsCache: InstantMetricResultsCache? = null,
    /**
     * # Whether to align the splits of instant metric query with splitByInterval and
     * # query's exec time. Useful when instant_metric_cache is enabled
     * # CLI flag: -querier.instant-metric-query-split-align
     * [instant_metric_query_split_align: <boolean> | default = false]
     */
    val instantMetricQuerySplitAlign: Boolean? = null,
    /**
     * # Cache series query results.
     * # CLI flag: -querier.cache-series-results
     * [cache_series_results: <boolean> | default = true]
     */
    val cacheSeriesResults: Boolean? = null,

    val seriesResultsCache: SeriesResultsCache? = null,
    /**
     * # Cache label query results.
     * # CLI flag: -querier.cache-label-results
     * [cache_label_results: <boolean> | default = true]
     */
    val cacheLabelResults: Boolean? = null,

    val labelResultsCache: LabelResultsCache? = null,
) {
    data class ResultsCache(
        val cache: CacheConfig? = null,
        val compression: String? = null,
    )

    data class IndexStatsResultsCache(
        val cache: CacheConfig? = null,
        val compression: String? = null,
    )

    data class VolumeResultsCache(
        val cache: CacheConfig? = null,
        val compression: String? = null,
    )

    data class InstantMetricResultsCache(
        val cache: CacheConfig? = null,
        val compression: String? = null,
    )

    data class SeriesResultsCache(
        val cache: CacheConfig? = null,
        val compression: String? = null,
    )

    data class LabelResultsCache(
        val cache: CacheConfig? = null,
        val compression: String? = null,
    )
}
