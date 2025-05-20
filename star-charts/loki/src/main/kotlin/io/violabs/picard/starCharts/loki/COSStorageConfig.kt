package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.cosStorageConfig.BackoffConfig
import io.violabs.picard.starCharts.loki.cosStorageConfig.HTTPConfig

@GeneratedDSL
data class COSStorageConfig(
    /**
     * # Set this to `true` to force the request to use path-style addressing.
     * # CLI flag: -<prefix>.cos.force-path-style
     * [forcepathstyle: <boolean> | default = false]
     */
    val forcePathStyle: Boolean? = null,
    /**
     * # Comma separated list of bucket names to evenly distribute chunks over.
     * # CLI flag: -<prefix>.cos.buckets
     * [bucketnames: <string> | default = ""]
     */
    val bucketNames: String? = null,
    /**
     * # COS Endpoint to connect to.
     * # CLI flag: -<prefix>.cos.endpoint
     * [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     * # COS region to use.
     * # CLI flag: -<prefix>.cos.region
     * [region: <string> | default = ""]
     */
    val region: String? = null,
    /**
     * # COS HMAC Access Key ID.
     * # CLI flag: -<prefix>.cos.access-key-id
     * [access_key_id: <string> | default = ""]
     */
    val accessKeyId: String? = null,
    /**
     * # COS HMAC Secret Access Key.
     * # CLI flag: -<prefix>.cos.secret-access-key
     * [secret_access_key: <string> | default = ""]
     */
    val secretAccessKey: String? = null,
    val httpConfig: HTTPConfig? = null,
    val backoffConfig: BackoffConfig? = null,
    /**
     * # IAM API key to access COS.
     * # CLI flag: -<prefix>.cos.api-key
     * [api_key: <string> | default = ""]
     */
    val apiKey: String? = null,
    /**
     * # COS service instance id to use.
     * # CLI flag: -<prefix>.cos.service-instance-id
     * [service_instance_id: <string> | default = ""]
     */
    val serviceInstanceId: String? = null,
    /**
     * # IAM Auth Endpoint for authentication.
     * # CLI flag: -<prefix>.cos.auth-endpoint
     * [auth_endpoint: <string> | default = "https://iam.cloud.ibm.com/identity/token"]
     */
    val authEndpoint: String? = null,
    /**
     * # Compute resource token file path.
     * # CLI flag: -<prefix>.cos.cr-token-file-path
     * [cr_token_file_path: <string> | default = ""]
     */
    val crTokenFilePath: String? = null,
    /**
     * # Name of the trusted profile.
     * # CLI flag: -<prefix>.cos.trusted-profile-name
     * [trusted_profile_name: <string> | default = ""]
     */
    val trustedProfileName: String? = null,
    /**
     * # ID of the trusted profile.
     * # CLI flag: -<prefix>.cos.trusted-profile-id
     * [trusted_profile_id: <string> | default = ""]
     */
    val trustedProfileId: String? = null,
)
