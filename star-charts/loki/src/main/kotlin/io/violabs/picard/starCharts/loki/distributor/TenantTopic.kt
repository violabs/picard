package io.violabs.picard.starCharts.loki.distributor

data class TenantTopic(
    /**
     * # Enable the tenant topic tee, which writes logs to Kafka topics based on
     * # tenant IDs instead of using multitenant topics/partitions.
     * # CLI flag: -distributor.tenant-topic-tee.enabled
     * [enabled: <boolean> | default = false]
     */
    val enabled: Boolean,
    /**
     * # Prefix to prepend to tenant IDs to form the final Kafka topic name
     * # CLI flag: -distributor.tenant-topic-tee.topic-prefix
     * [topic_prefix: <string> | default = "loki.tenant"]
     */
    val topicPrefix: String? = null,
    /**
     * # Maximum number of bytes that can be buffered before producing to Kafka
     * # CLI flag: -distributor.tenant-topic-tee.max-buffered-bytes
     * [max_buffered_bytes: <int> | default = 100MiB]
     */
    val maxBuggeredBytes: Int? = null,
    /**
     * # Maximum size of a single Kafka record in bytes
     * # CLI flag: -distributor.tenant-topic-tee.max-record-size-bytes
     * [max_record_size_bytes: <int> | default = 15MiB249KiB]
     */
    val maxRecordSizeBytes: Int? = null,
    /**
     * # Topic strategy to use. Valid values are 'simple' or 'automatic'
     * # CLI flag: -distributor.tenant-topic-tee.strategy
     * [strategy: <string> | default = "simple"]
     */
    val strategy: String? = null
)