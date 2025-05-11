package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.picard.domain.k8sResources.Quantity

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Querier(
    /**
     * Maximum duration for which the live tailing requests are served.
     * CLI flag: -querier.tail-max-duration
     */
    val tailMaxDuration: Quantity? = null,
    /**
     * Time to wait before sending more than the minimum successful query requests.
     * CLI flag: -querier.extra-query-delay
     */
    val extraQueryDelay: Quantity? = null,
    /**
     * Maximum lookback beyond which queries are not sent to ingester. 0 means all
     * queries are sent to ingester.
     * CLI flag: -querier.query-ingesters-within
     */
    val queryIngestersWithin: Quantity? = null,
    val engine: Engine? = null,
    /**
     * The maximum number of queries that can be simultaneously processed by the
     * querier.
     * CLI flag: -querier.max-concurrent
     */
    val maxConcurrent: Int? = null,
    /**
     * Only query the store, and not attempt any ingesters. This is useful for
     * running a standalone querier pool operating only against stored data.
     * CLI flag: -querier.query-store-only
     */
    val queryStoreOnly: Boolean? = null,
    /**
     * When true, queriers only query the ingesters, and not stored data. This is
     * useful when the object store is unavailable.
     * CLI flag: -querier.query-ingester-only
     */
    val queryIngesterOnly: Boolean? = null,
    /**
     *  When true, allow queries to span multiple tenants.
     * CLI flag: -querier.multi-tenant-queries-enabled
     */
    val multiTenantQueriesEnabled: Boolean? = null,
    /**
     * When true, querier limits sent via a header are enforced.
     * CLI flag: -querier.per-request-limits-enabled
     */
    val perRequestLimitsEnabled: Boolean? = null,
    /**
     * When true, querier directs ingester queries to the partition-ingesters instead
     * of the normal ingesters.
     * CLI flag: -querier.query-partition-ingesters
     */
    val queryPartitionIngesters: Boolean? = null
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Engine(
        /**
         * The maximum amount of time to look back for log lines. Used only for instant
         * log queries.
         * CLI flag: -querier.engine.max-lookback-period
         */
        val maxLookBackPeriod: Quantity? = null,
        /**
         * The maximum number of labels the heap of a topk query using a count min
         * sketch can track.
         * CLI flag: -querier.engine.max-count-min-sketch-heap-size
         */
        val maxCountMinSketchHeapSize: Int? = null
    )
}