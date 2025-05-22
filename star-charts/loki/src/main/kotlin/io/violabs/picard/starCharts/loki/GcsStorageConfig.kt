package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(
    withMapGroup = "SINGLE"
)
data class GcsStorageConfig(
    /**
     * # Name of GCS bucket. Please refer to
     * # https://cloud.google.com/docs/authentication/production for more information
     * # about how to configure authentication.
     * # CLI flag: -<prefix>.gcs.bucketname
     * [bucket_name: <string> | default = ""]
     */
    val bucketName: String? = null,
    /**
     * # Custom GCS endpoint URL.
     * # CLI flag: -<prefix>.gcs.endpoint
     * [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     * # Service account key content in JSON format, refer to
     * # https://cloud.google.com/iam/docs/creating-managing-service-account-keys for
     * # creation.
     * # CLI flag: -<prefix>.gcs.service-account
     * [service_account: <string> | default = ""]
     */
    val serviceAccount: String? = null,
    /**
     * # The size of the buffer that GCS client for each PUT request. 0 to disable
     * # buffering.
     * # CLI flag: -<prefix>.gcs.chunk-buffer-size
     * [chunk_buffer_size: <int> | default = 0]
     */
    val chunkBufferSize: Int? = null,
    /**
     * # The duration after which the requests to GCS should be timed out.
     * # CLI flag: -<prefix>.gcs.request-timeout
     * [request_timeout: <duration> | default = 0s]
     */
    val requestTimeout: Duration? = null,
    /**
     * # Enable OpenCensus (OC) instrumentation for all requests.
     * # CLI flag: -<prefix>.gcs.enable-opencensus
     * [enable_opencensus: <boolean> | default = true]
     */
    val enableOpencensus: Boolean? = null,
    /**
     * # Enable HTTP2 connections.
     * # CLI flag: -<prefix>.gcs.enable-http2
     * [enable_http2: <boolean> | default = true]
     */
    val enableHttp2: Boolean? = null,
    /**
     * # Enable automatic retries of failed idempotent requests.
     * # CLI flag: -<prefix>.gcs.enable-retries
     * [enable_retries: <boolean> | default = true]
     */
    val enableRetries: Boolean? = null,
)
