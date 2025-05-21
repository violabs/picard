package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.cacheConfig.CacheConfig

@GeneratedDSL
data class ChunkStorageConfig(
    /**
     * # The cache_config block configures the cache backend for a specific Loki
     * # component.
     * # The CLI flags prefix for this block configuration is: store.chunks-cache
     * [chunk_cache_config: <cache_config>]
     */
    val chunkCacheConfig: CacheConfig? = null,
    /**
     * # The cache_config block configures the cache backend for a specific Loki
     * # component.
     * # The CLI flags prefix for this block configuration is: store.chunks-cache-l2
     * [chunk_cache_config_l2: <cache_config>]
     */
    val chunkCacheConfigL2: CacheConfig? = null,
    /**
     * # Write dedupe cache is deprecated along with legacy index types (aws,
     * # aws-dynamo, bigtable, bigtable-hashed, cassandra, gcp, gcp-columnkey,
     * # grpc-store).
     * # Consider using TSDB index which does not require a write dedupe cache.
     * # The CLI flags prefix for this block configuration is: store.index-cache-write
     * [write_dedupe_cache_config: <cache_config>]
     */
    val writeDedupeCacheConfig: CacheConfig? = null,
    /**
     * # Chunks fetched from queriers before this duration will not be written to the
     * # cache. A value of 0 will write all chunks to the cache
     * # CLI flag: -store.skip-query-writeback-older-than
     * [skip_query_writeback_cache_older_than: <duration> | default = 0s]
     */
    val skipQueryWritebackCacheOlderThan: Duration? = null,
    /**
     * # Chunks will be handed off to the L2 cache after this duration. 0 to disable L2
     * # cache.
     * # CLI flag: -store.chunks-cache-l2.handoff
     * [l2_chunk_cache_handoff: <duration> | default = 0s]
     */
    val l2ChunkCacheHandoff: Duration? = null,
    /**
     * # Cache index entries older than this period. 0 to disable.
     * # CLI flag: -store.cache-lookups-older-than
     * [cache_lookups_older_than: <duration> | default = 0s]
     */
    val cacheLookupsOlderThan: Duration? = null,
)
