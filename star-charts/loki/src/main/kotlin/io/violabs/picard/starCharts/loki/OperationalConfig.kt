package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class OperationalConfig(
    /**
     * # Log every new stream created by a push request (very verbose, recommend to
     * # enable via runtime config only).
     * # CLI flag: -operation-config.log-stream-creation
     * [log_stream_creation: <boolean> | default = false]
     */
    val logStreamCreation: Boolean? = null,
    /**
     * # Log every push request (very verbose, recommend to enable via runtime config
     * # only).
     * # CLI flag: -operation-config.log-push-request
     * [log_push_request: <boolean> | default = false]
     */
    val logPushRequest: Boolean? = null,
    /**
     * # Log every stream in a push request (very verbose, recommend to enable via
     * # runtime config only).
     * # CLI flag: -operation-config.log-push-request-streams
     * [log_push_request_streams: <boolean> | default = false]
     */
    val logPushRequestStreams: Boolean? = null,
    /**
     * # Log metrics for duplicate lines received.
     * # CLI flag: -operation-config.log-duplicate-metrics
     * [log_duplicate_metrics: <boolean> | default = false]
     */
    val logDuplicateMetrics: Boolean? = null,
    /**
     * # Log stream info for duplicate lines received
     * # CLI flag: -operation-config.log-duplicate-stream-info
     * [log_duplicate_stream_info: <boolean> | default = false]
     */
    val logDuplicateStreamInfo: Boolean? = null,
    /**
     * # Log push errors with a rate limited logger, will show client push errors
     * # without overly spamming logs.
     * # CLI flag: -operation-config.limited-log-push-errors
     * [limited_log_push_errors: <boolean> | default = true]
     */
    val limitedLogPushErrors: Boolean? = null,
)
