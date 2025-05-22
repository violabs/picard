package io.violabs.picard.starCharts.loki.s3StorageConfig

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Sse
import java.net.URL

@GeneratedDsl
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
    val sse: Sse? = null,
    val backoffConfig: BackoffConfig? = null,
    /**
     * # Disable forcing S3 dualstack endpoint usage.
     * # CLI flag: -<prefix>.s3.disable-dualstack
     * [disable_dualstack: <boolean> | default = false]
     */
    val disableDualstack: Boolean? = null
) {

}