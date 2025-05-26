package io.violabs.picard.starCharts.loki.distributor

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.ring.Ring

@GeneratedDsl
data class Distributor(
    val ring: Ring? = null,
    /**
     * # Number of workers to push batches to ingesters.
     * # CLI flag: -distributor.push-worker-count
     * [push_worker_count: <int> | default = 256]
     */
    val pushWorkerCount: Int? = null,
    val rateStore: RateStore? = null,
    /**
     * Customize the logging of write failures.
     */
    val writeFailuresLogging: WriteFailuresLogging? = null,
    val otlpConfig: OTLPConfig? = null,
    /**
     * # Enable writes to Kafka during Push requests.
     * # CLI flag: -distributor.kafka-writes-enabled
     * [kafka_writes_enabled: <boolean> | default = false]
     */
    val kafkaWritesEnabled: Boolean? = null,
    /**
     * # Enable writes to Ingesters during Push requests. Defaults to true.
     * # CLI flag: -distributor.ingester-writes-enabled
     * [ingester_writes_enabled: <boolean> | default = true]
     */
    val ingesterWritesEnabled: Boolean? = null,
    /**
     * # Enable checking limits against the ingest-limits service. Defaults to false.
     * # CLI flag: -distributor.ingest-limits-enabled
     * [ingest_limits_enabled: <boolean> | default = false]
     */
    val ingestLimitsEnabled: Boolean? = null,
    /**
     * # Enable dry-run mode where limits are checked the ingest-limits service, but
     * # not enforced. Defaults to false.
     * # CLI flag: -distributor.ingest-limits-dry-run-enabled
     * [ingest_limits_dry_run_enabled: <boolean> | default = false]
     */
    val ingestLimitsDryRunEnabled: Boolean? = null,
    val tenantTopic: TenantTopic? = null
)