package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.picard.domain.k8sResources.Quantity
import java.net.URL

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class S3StorageConfig(
    /**
     * # S3 endpoint URL with escaped Key and Secret encoded. If only region is
     * # specified as a host, proper endpoint will be deduced. Use
     * # inmemory:///<bucket-name> to use a mock in-memory implementation.
     * # CLI flag: -<prefix>.s3.url
     * [s3: <url>]
     */
    val s3: URL? = null,
    /**
     * # Set this to `true` to force the request to use path-style addressing.
     * # CLI flag: -<prefix>.s3.force-path-style
     * [s3forcepathstyle: <boolean> | default = false]
     */
    @JsonProperty("s3forcepathstyle")
    val s3ForcePathStyle: Boolean? = null,
    /**
     * # Comma separated list of bucket names to evenly distribute chunks over.
     * # Overrides any buckets specified in s3.url flag
     * # CLI flag: -<prefix>.s3.buckets
     * [bucketnames: <string> | default = ""]
     */
    @JsonProperty("bucketnames")
    val bucketNames: String? = null,
    /**
     *
     * # S3 Endpoint to connect to.
     * # CLI flag: -<prefix>.s3.endpoint
     * [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     * # AWS region to use.
     * # CLI flag: -<prefix>.s3.region
     * [region: <string> | default = ""]
     */
    val region: String? = null,
    /**
     * # AWS Access Key ID
     * # CLI flag: -<prefix>.s3.access-key-id
     * [access_key_id: <string> | default = ""]
     */
    val accessKeyId: String? = null,
    /**
     * # AWS Secret Access Key
     * # CLI flag: -<prefix>.s3.secret-access-key
     * [secret_access_key: <string> | default = ""]
     */
    val secretAccessKey: String? = null,
    /**
     * # AWS Session Token
     * # CLI flag: -<prefix>.s3.session-token
     * [session_token: <string> | default = ""]
     */
    val sessionToken: String? = null,
    /**
     * # Disable https on s3 connection.
     * # CLI flag: -<prefix>.s3.insecure
     * [insecure: <boolean> | default = false]
     */
    val insecure: Boolean? = null,
    /**
     * # Delimiter used to replace the default delimiter ':' in chunk IDs when storing
     * # chunks. This is mainly intended when you run a MinIO instance on a Windows
     * # machine. You should not change this value inflight.
     * # CLI flag: -<prefix>.s3.chunk-delimiter
     * [chunk_delimiter: <string> | default = ""]
     */
    val chunkDelimiter: String? = null,
    val httpConfig: HttpConfig? = null,
    /**
     * # The signature version to use for authenticating against S3. Supported values
     * # are: v4.
     * # CLI flag: -<prefix>.s3.signature-version
     * [signature_version: <string> | default = "v4"]
     */
    val signatureVersion: String? = null,
    /**
     * # The S3 storage class which objects will use. Supported values are: GLACIER,
     * # DEEP_ARCHIVE, GLACIER_IR, INTELLIGENT_TIERING, ONEZONE_IA, OUTPOSTS,
     * # REDUCED_REDUNDANCY, STANDARD, STANDARD_IA.
     * # CLI flag: -<prefix>.s3.storage-class
     * [storage_class: <string> | default = "STANDARD"]
     */
    val storageClass: String? = null,
    val sse: SSE? = null,
    val backoffConfig: BackoffConfig? = null,
    /**
     * # Disable forcing S3 dualstack endpoint usage.
     * # CLI flag: -<prefix>.s3.disable-dualstack
     * [disable_dualstack: <boolean> | default = false]
     */
    val disableDualstack: Boolean? = null
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class HttpConfig(
        /**
         *   # Timeout specifies a time limit for requests made by s3 Client.
         *   # CLI flag: -<prefix>.s3.http.timeout
         *   [timeout: <duration> | default = 0s]
         */
        val timeout: Quantity? = null,
        /**
         *   # The maximum amount of time an idle connection will be held open.
         *   # CLI flag: -<prefix>.s3.http.idle-conn-timeout
         *   [idle_conn_timeout: <duration> | default = 1m30s]
         */
        val idleConnTimeout: Quantity? = null,
        /**
         *   # If non-zero, specifies the amount of time to wait for a server's response
         *   # headers after fully writing the request.
         *   # CLI flag: -<prefix>.s3.http.response-header-timeout
         *   [response_header_timeout: <duration> | default = 0s]
         */
        val responseHeaderTimeout: Quantity? = null,
        /**
         *   # Set to true to skip verifying the certificate chain and hostname.
         *   # CLI flag: -<prefix>.s3.http.insecure-skip-verify
         *   [insecure_skip_verify: <boolean> | default = false]
         */
        val insecureSkipVerify: Boolean? = null,
        /**
         *   # Path to the trusted CA file that signed the SSL certificate of the S3
         *   # endpoint.
         *   # CLI flag: -<prefix>.s3.http.ca-file
         *   [ca_file: <string> | default = ""]
         */
        val caFile: String? = null
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class SSE(
        /**
         *   # Enable AWS Server Side Encryption. Supported values: SSE-KMS, SSE-S3.
         *   # CLI flag: -<prefix>.s3.sse.type
         *   [type: <string> | default = ""]
         */
        val type: Type? = null,
        /**
         *   # KMS Key ID used to encrypt objects in S3
         *   # CLI flag: -<prefix>.s3.sse.kms-key-id
         *   [kms_key_id: <string> | default = ""]
         */
        val kmsKeyId: String? = null,
        /**
         *   # KMS Encryption Context used for object encryption. It expects JSON formatted
         *   # string.
         *   # CLI flag: -<prefix>.s3.sse.kms-encryption-context
         *   [kms_encryption_context: <string> | default = ""]
         */
        val kmsEncryptionContext: String? = null,
    ) {
        @JsonNaming(PropertyNamingStrategies.KebabCaseStrategy::class)
        enum class Type {
            SSE_KMS, SSE_S3;
        }
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class BackoffConfig(
        /**
         * # Minimum backoff time when s3 get Object
         * # CLI flag: -<prefix>.s3.min-backoff
         * [min_period: <duration> | default = 100ms]
         */
        val minPeriod: Quantity? = null,
        /**
         * # Maximum backoff time when s3 get Object
         * # CLI flag: -<prefix>.s3.max-backoff
         * [max_period: <duration> | default = 3s]
         */
        val maxPeriod: Quantity? = null,
        /**
         * # Maximum number of times to retry for s3 GetObject or ObjectExists
         * # CLI flag: -<prefix>.s3.max-retries
         * [max_retries: <int> | default = 5]
         */
        val maxRetries: Int? = null,
        /**
         * # Disable forcing S3 dualstack endpoint usage.
         * # CLI flag: -<prefix>.s3.disable-dualstack
         * [disable_dualstack: <boolean> | default = false]
         */
        val disableDualstack: Boolean? = null,
    )
}