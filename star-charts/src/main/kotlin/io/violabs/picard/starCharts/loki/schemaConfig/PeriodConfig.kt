package io.violabs.picard.starCharts.loki.schemaConfig

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.picard.domain.k8sResources.Quantity
import java.time.LocalDate

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PeriodConfig(
    /**
     * The date of the first day that index buckets should be created. Use a date in
     * the past if this is your only period_config, otherwise use a date when you
     * want the schema to switch over. In YYYY-MM-DD format, for example: 2018-04-15.
     */
    val from: LocalDate,
    /**
     * store and object_store below affect which <storage_config> key is used. Which
     * index to use. Either tsdb or boltdb-shipper. Following stores are deprecated:
     * aws, aws-dynamo, gcp, gcp-columnkey, bigtable, bigtable-hashed, cassandra,
     * grpc.
     */
    val store: String? = null,
    /**
     * Which store to use for the chunks. Either aws (alias s3), azure, gcs,
     * alibabacloud, bos, cos, swift, filesystem, or a named_store (refer to
     * named_stores_config). Following stores are deprecated: aws-dynamo, gcp,
     * gcp-columnkey, bigtable, bigtable-hashed, cassandra, grpc.
     */
    @JsonProperty("object_store")
    val objectStore: String? = null,
    /**
     * # The schema version to use, current recommended schema is v13.
     */
    val schema: String? = null,
    /**
     * Configures how the index is updated and stored.
     */
    val index: Index? = null,
    /**
     * Configured how the chunks are updated and stored.
     */
    val chunk: Chunk? = null,
    /**
     * How many shards will be created. Only used if schema is v10 or greater.
     */
    @JsonProperty("row_shards")
    val rowShards: Int? = null
) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Index(
        /**
         * Path prefix for index tables. Prefix always needs to end with a path
         * delimiter '/', except when the prefix is empty.
         */
        @JsonProperty("path_prefix")
        val pathPrefix: String? = null,
        /**
         * Table prefix for all period tables.
         */
        val prefix: String? = null,
        /**
         * Table period.
         */
        val period: Quantity? = null,
        /**
         * A map to be added to all managed tables.
         */
        val tags: Map<String, String>? = null
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Chunk(
        /**
         * Table prefix for all period tables.
         */
        val prefix: String? = null,
        /**
         * Table period.
         */
        val period: Quantity? = null,
        /**
         * A map to be added to all managed tables.
         */
        val tags: Map<String, String>? = null
    )
}