package io.violabs.picard.starCharts.loki

class COSStorageConfig(
    /**
     * # Set this to `true` to force the request to use path-style addressing.
     * # CLI flag: -<prefix>.cos.force-path-style
     * [forcepathstyle: <boolean> | default = false]
     *
     * # Comma separated list of bucket names to evenly distribute chunks over.
     * # CLI flag: -<prefix>.cos.buckets
     * [bucketnames: <string> | default = ""]
     *
     * # COS Endpoint to connect to.
     * # CLI flag: -<prefix>.cos.endpoint
     * [endpoint: <string> | default = ""]
     *
     * # COS region to use.
     * # CLI flag: -<prefix>.cos.region
     * [region: <string> | default = ""]
     *
     * # COS HMAC Access Key ID.
     * # CLI flag: -<prefix>.cos.access-key-id
     * [access_key_id: <string> | default = ""]
     *
     * # COS HMAC Secret Access Key.
     * # CLI flag: -<prefix>.cos.secret-access-key
     * [secret_access_key: <string> | default = ""]
     *
     * http_config:
     *   # The maximum amount of time an idle connection will be held open.
     *   # CLI flag: -<prefix>.cos.http.idle-conn-timeout
     *   [idle_conn_timeout: <duration> | default = 1m30s]
     *
     *   # If non-zero, specifies the amount of time to wait for a server's response
     *   # headers after fully writing the request.
     *   # CLI flag: -<prefix>.cos.http.response-header-timeout
     *   [response_header_timeout: <duration> | default = 0s]
     *
     * # Configures back off when cos get Object.
     * backoff_config:
     *   # Minimum backoff time when cos get Object.
     *   # CLI flag: -<prefix>.cos.min-backoff
     *   [min_period: <duration> | default = 100ms]
     *
     *   # Maximum backoff time when cos get Object.
     *   # CLI flag: -<prefix>.cos.max-backoff
     *   [max_period: <duration> | default = 3s]
     *
     *   # Maximum number of times to retry when cos get Object.
     *   # CLI flag: -<prefix>.cos.max-retries
     *   [max_retries: <int> | default = 5]
     *
     * # IAM API key to access COS.
     * # CLI flag: -<prefix>.cos.api-key
     * [api_key: <string> | default = ""]
     *
     * # COS service instance id to use.
     * # CLI flag: -<prefix>.cos.service-instance-id
     * [service_instance_id: <string> | default = ""]
     *
     * # IAM Auth Endpoint for authentication.
     * # CLI flag: -<prefix>.cos.auth-endpoint
     * [auth_endpoint: <string> | default = "https://iam.cloud.ibm.com/identity/token"]
     *
     * # Compute resource token file path.
     * # CLI flag: -<prefix>.cos.cr-token-file-path
     * [cr_token_file_path: <string> | default = ""]
     *
     * # Name of the trusted profile.
     * # CLI flag: -<prefix>.cos.trusted-profile-name
     * [trusted_profile_name: <string> | default = ""]
     *
     * # ID of the trusted profile.
     * # CLI flag: -<prefix>.cos.trusted-profile-id
     * [trusted_profile_id: <string> | default = ""]
     */
) {
}