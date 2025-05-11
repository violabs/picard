package io.violabs.picard.starCharts.loki

class ThanosObjectStoreConfig(
    /**
     * s3:
     *   # The S3 bucket endpoint. It could be an AWS S3 endpoint listed at
     *   # https://docs.aws.amazon.com/general/latest/gr/s3.html or the address of an
     *   # S3-compatible service in hostname:port format.
     *   # CLI flag: -<prefix>.s3.endpoint
     *   [endpoint: <string> | default = ""]
     *
     *   # S3 region. If unset, the client will issue a S3 GetBucketLocation API call
     *   # to autodetect it.
     *   # CLI flag: -<prefix>.s3.region
     *   [region: <string> | default = ""]
     *
     *   # S3 bucket name
     *   # CLI flag: -<prefix>.s3.bucket-name
     *   [bucket_name: <string> | default = ""]
     *
     *   # S3 secret access key
     *   # CLI flag: -<prefix>.s3.secret-access-key
     *   [secret_access_key: <string> | default = ""]
     *
     *   # S3 access key ID
     *   # CLI flag: -<prefix>.s3.access-key-id
     *   [access_key_id: <string> | default = ""]
     *
     *   # S3 session token
     *   # CLI flag: -<prefix>.s3.session-token
     *   [session_token: <string> | default = ""]
     *
     *   # If enabled, use http:// for the S3 endpoint instead of https://. This could
     *   # be useful in local dev/test environments while using an S3-compatible
     *   # backend storage, like Minio.
     *   # CLI flag: -<prefix>.s3.insecure
     *   [insecure: <boolean> | default = false]
     *
     *   # Use a specific version of the S3 list object API. Supported values are v1 or
     *   # v2. Default is unset.
     *   # CLI flag: -<prefix>.s3.list-objects-version
     *   [list_objects_version: <string> | default = ""]
     *
     *   # Bucket lookup style type, used to access bucket in S3-compatible service.
     *   # Default is auto. Supported values are: auto, path, virtual-hosted.
     *   # CLI flag: -<prefix>.s3.bucket-lookup-type
     *   [bucket_lookup_type: <int> | default = auto]
     *
     *   # When enabled, direct all AWS S3 requests to the dual-stack IPv4/IPv6
     *   # endpoint for the configured region.
     *   # CLI flag: -<prefix>.s3.dualstack-enabled
     *   [dualstack_enabled: <boolean> | default = true]
     *
     *   # The S3 storage class to use, not set by default. Details can be found at
     *   # https://aws.amazon.com/s3/storage-classes/. Supported values are: STANDARD,
     *   # REDUCED_REDUNDANCY, GLACIER, STANDARD_IA, ONEZONE_IA, INTELLIGENT_TIERING,
     *   # DEEP_ARCHIVE, OUTPOSTS, GLACIER_IR, SNOW, EXPRESS_ONEZONE
     *   # CLI flag: -<prefix>.s3.storage-class
     *   [storage_class: <string> | default = ""]
     *
     *   # If enabled, it will use the default authentication methods of the AWS SDK
     *   # for go based on known environment variables and known AWS config files.
     *   # CLI flag: -<prefix>.s3.native-aws-auth-enabled
     *   [native_aws_auth_enabled: <boolean> | default = false]
     *
     *   # The minimum file size in bytes used for multipart uploads. If 0, the value
     *   # is optimally computed for each object.
     *   # CLI flag: -<prefix>.s3.part-size
     *   [part_size: <int> | default = 0]
     *
     *   # If enabled, a Content-MD5 header is sent with S3 Put Object requests.
     *   # Consumes more resources to compute the MD5, but may improve compatibility
     *   # with object storage services that do not support checksums.
     *   # CLI flag: -<prefix>.s3.send-content-md5
     *   [send_content_md5: <boolean> | default = false]
     *
     *   # Accessing S3 resources using temporary, secure credentials provided by AWS
     *   # Security Token Service.
     *   # CLI flag: -<prefix>.s3.sts-endpoint
     *   [sts_endpoint: <string> | default = ""]
     *
     *   # The maximum number of retries for S3 requests that are retryable. Default is
     *   # 10, set this to 1 to disable retries.
     *   # CLI flag: -<prefix>.s3.max-retries
     *   [max_retries: <int> | default = 10]
     *
     *   sse:
     *     # Enable AWS Server Side Encryption. Supported values: SSE-KMS, SSE-S3.
     *     # CLI flag: -<prefix>.s3.sse.type
     *     [type: <string> | default = ""]
     *
     *     # KMS Key ID used to encrypt objects in S3
     *     # CLI flag: -<prefix>.s3.sse.kms-key-id
     *     [kms_key_id: <string> | default = ""]
     *
     *     # KMS Encryption Context used for object encryption. It expects JSON
     *     # formatted string.
     *     # CLI flag: -<prefix>.s3.sse.kms-encryption-context
     *     [kms_encryption_context: <string> | default = ""]
     *
     *   http:
     *     # The time an idle connection will remain idle before closing.
     *     # CLI flag: -<prefix>.s3.http.idle-conn-timeout
     *     [idle_conn_timeout: <duration> | default = 1m30s]
     *
     *     # The amount of time the client will wait for a servers response headers.
     *     # CLI flag: -<prefix>.s3.http.response-header-timeout
     *     [response_header_timeout: <duration> | default = 2m]
     *
     *     # If the client connects via HTTPS and this option is enabled, the client
     *     # will accept any certificate and hostname.
     *     # CLI flag: -<prefix>.s3.http.insecure-skip-verify
     *     [insecure_skip_verify: <boolean> | default = false]
     *
     *     # Maximum time to wait for a TLS handshake. 0 means no limit.
     *     # CLI flag: -<prefix>.s3.tls-handshake-timeout
     *     [tls_handshake_timeout: <duration> | default = 10s]
     *
     *     # The time to wait for a server's first response headers after fully writing
     *     # the request headers if the request has an Expect header. 0 to send the
     *     # request body immediately.
     *     # CLI flag: -<prefix>.s3.expect-continue-timeout
     *     [expect_continue_timeout: <duration> | default = 1s]
     *
     *     # Maximum number of idle (keep-alive) connections across all hosts. 0 means
     *     # no limit.
     *     # CLI flag: -<prefix>.s3.max-idle-connections
     *     [max_idle_connections: <int> | default = 100]
     *
     *     # Maximum number of idle (keep-alive) connections to keep per-host. If 0, a
     *     # built-in default value is used.
     *     # CLI flag: -<prefix>.s3.max-idle-connections-per-host
     *     [max_idle_connections_per_host: <int> | default = 100]
     *
     *     # Maximum number of connections per host. 0 means no limit.
     *     # CLI flag: -<prefix>.s3.max-connections-per-host
     *     [max_connections_per_host: <int> | default = 0]
     *
     *     # Path to the CA certificates to validate server certificate against. If not
     *     # set, the host's root CA certificates are used.
     *     # CLI flag: -<prefix>.s3.http.tls-ca-path
     *     [tls_ca_path: <string> | default = ""]
     *
     *     # Path to the client certificate, which will be used for authenticating with
     *     # the server. Also requires the key path to be configured.
     *     # CLI flag: -<prefix>.s3.http.tls-cert-path
     *     [tls_cert_path: <string> | default = ""]
     *
     *     # Path to the key for the client certificate. Also requires the client
     *     # certificate to be configured.
     *     # CLI flag: -<prefix>.s3.http.tls-key-path
     *     [tls_key_path: <string> | default = ""]
     *
     *     # Override the expected name on the server certificate.
     *     # CLI flag: -<prefix>.s3.http.tls-server-name
     *     [tls_server_name: <string> | default = ""]
     *
     *   trace:
     *     # When enabled, low-level S3 HTTP operation information is logged at the
     *     # debug level.
     *     # CLI flag: -<prefix>.s3.trace.enabled
     *     [enabled: <boolean> | default = false]
     *
     * gcs:
     *   # GCS bucket name
     *   # CLI flag: -<prefix>.gcs.bucket-name
     *   [bucket_name: <string> | default = ""]
     *
     *   # JSON either from a Google Developers Console client_credentials.json file,
     *   # or a Google Developers service account key. Needs to be valid JSON, not a
     *   # filesystem path. If empty, fallback to Google default logic:
     *   # 1. A JSON file whose path is specified by the GOOGLE_APPLICATION_CREDENTIALS
     *   # environment variable. For workload identity federation, refer to
     *   # https://cloud.google.com/iam/docs/how-to#using-workload-identity-federation
     *   # on how to generate the JSON configuration file for on-prem/non-Google cloud
     *   # platforms.
     *   # 2. A JSON file in a location known to the gcloud command-line tool:
     *   # $HOME/.config/gcloud/application_default_credentials.json.
     *   # 3. On Google Compute Engine it fetches credentials from the metadata server.
     *   # CLI flag: -<prefix>.gcs.service-account
     *   [service_account: <string> | default = ""]
     *
     *   # The maximum size of the buffer that GCS client for a single PUT request. 0
     *   # to disable buffering.
     *   # CLI flag: -<prefix>.gcs.chunk-buffer-size
     *   [chunk_buffer_size: <int> | default = 0]
     *
     *   # The maximum number of retries for idempotent operations. Overrides the
     *   # default gcs storage client behavior if this value is greater than 0. Set
     *   # this to 1 to disable retries.
     *   # CLI flag: -<prefix>.gcs.max-retries
     *   [max_retries: <int> | default = 10]
     *
     * azure:
     *   # Azure storage account name
     *   # CLI flag: -<prefix>.azure.account-name
     *   [account_name: <string> | default = ""]
     *
     *   # Azure storage account key. If unset, Azure managed identities will be used
     *   # for authentication instead.
     *   # CLI flag: -<prefix>.azure.account-key
     *   [account_key: <string> | default = ""]
     *
     *   # If `connection-string` is set, the value of `endpoint-suffix` will not be
     *   # used. Use this method over `account-key` if you need to authenticate via a
     *   # SAS token. Or if you use the Azurite emulator.
     *   # CLI flag: -<prefix>.azure.connection-string
     *   [connection_string: <string> | default = ""]
     *
     *   # Azure storage container name
     *   # CLI flag: -<prefix>.azure.container-name
     *   [container_name: <string> | default = ""]
     *
     *   # Azure storage endpoint suffix without schema. The account name will be
     *   # prefixed to this value to create the FQDN. If set to empty string, default
     *   # endpoint suffix is used.
     *   # CLI flag: -<prefix>.azure.endpoint-suffix
     *   [endpoint_suffix: <string> | default = ""]
     *
     *   # Number of retries for recoverable errors
     *   # CLI flag: -<prefix>.azure.max-retries
     *   [max_retries: <int> | default = 20]
     *
     *   # User assigned managed identity. If empty, then System assigned identity is
     *   # used.
     *   # CLI flag: -<prefix>.azure.user-assigned-id
     *   [user_assigned_id: <string> | default = ""]
     *
     *   # Delimiter used to replace ':' in chunk IDs when storing chunks
     *   # CLI flag: -<prefix>.azure.chunk-delimiter
     *   [chunk_delimiter: <string> | default = "-"]
     *
     * swift:
     *   # OpenStack Swift application credential id
     *   # CLI flag: -<prefix>.swift.application-credential-id
     *   [application_credential_id: <string> | default = ""]
     *
     *   # OpenStack Swift application credential name
     *   # CLI flag: -<prefix>.swift.application-credential-name
     *   [application_credential_name: <string> | default = ""]
     *
     *   # OpenStack Swift application credential secret
     *   # CLI flag: -<prefix>.swift.application-credential-secret
     *   [application_credential_secret: <string> | default = ""]
     *
     *   # OpenStack Swift authentication API version. 0 to autodetect.
     *   # CLI flag: -<prefix>.swift.auth-version
     *   [auth_version: <int> | default = 0]
     *
     *   # OpenStack Swift authentication URL
     *   # CLI flag: -<prefix>.swift.auth-url
     *   [auth_url: <string> | default = ""]
     *
     *   # OpenStack Swift username.
     *   # CLI flag: -<prefix>.swift.username
     *   [username: <string> | default = ""]
     *
     *   # OpenStack Swift user's domain name.
     *   # CLI flag: -<prefix>.swift.user-domain-name
     *   [user_domain_name: <string> | default = ""]
     *
     *   # OpenStack Swift user's domain ID.
     *   # CLI flag: -<prefix>.swift.user-domain-id
     *   [user_domain_id: <string> | default = ""]
     *
     *   # OpenStack Swift user ID.
     *   # CLI flag: -<prefix>.swift.user-id
     *   [user_id: <string> | default = ""]
     *
     *   # OpenStack Swift API key.
     *   # CLI flag: -<prefix>.swift.password
     *   [password: <string> | default = ""]
     *
     *   # OpenStack Swift user's domain ID.
     *   # CLI flag: -<prefix>.swift.domain-id
     *   [domain_id: <string> | default = ""]
     *
     *   # OpenStack Swift user's domain name.
     *   # CLI flag: -<prefix>.swift.domain-name
     *   [domain_name: <string> | default = ""]
     *
     *   # OpenStack Swift project ID (v2,v3 auth only).
     *   # CLI flag: -<prefix>.swift.project-id
     *   [project_id: <string> | default = ""]
     *
     *   # OpenStack Swift project name (v2,v3 auth only).
     *   # CLI flag: -<prefix>.swift.project-name
     *   [project_name: <string> | default = ""]
     *
     *   # ID of the OpenStack Swift project's domain (v3 auth only), only needed if it
     *   # differs the from user domain.
     *   # CLI flag: -<prefix>.swift.project-domain-id
     *   [project_domain_id: <string> | default = ""]
     *
     *   # Name of the OpenStack Swift project's domain (v3 auth only), only needed if
     *   # it differs from the user domain.
     *   # CLI flag: -<prefix>.swift.project-domain-name
     *   [project_domain_name: <string> | default = ""]
     *
     *   # OpenStack Swift Region to use (v2,v3 auth only).
     *   # CLI flag: -<prefix>.swift.region-name
     *   [region_name: <string> | default = ""]
     *
     *   # Name of the OpenStack Swift container to put chunks in.
     *   # CLI flag: -<prefix>.swift.container-name
     *   [container_name: <string> | default = ""]
     *
     *   # Max retries on requests error.
     *   # CLI flag: -<prefix>.swift.max-retries
     *   [max_retries: <int> | default = 3]
     *
     *   # Time after which a connection attempt is aborted.
     *   # CLI flag: -<prefix>.swift.connect-timeout
     *   [connect_timeout: <duration> | default = 10s]
     *
     *   # Time after which an idle request is aborted. The timeout watchdog is reset
     *   # each time some data is received, so the timeout triggers after X time no
     *   # data is received on a request.
     *   # CLI flag: -<prefix>.swift.request-timeout
     *   [request_timeout: <duration> | default = 5s]
     *
     *   http:
     *     # The time an idle connection will remain idle before closing.
     *     # CLI flag: -<prefix>.swift.http.idle-conn-timeout
     *     [idle_conn_timeout: <duration> | default = 1m30s]
     *
     *     # The amount of time the client will wait for a servers response headers.
     *     # CLI flag: -<prefix>.swift.http.response-header-timeout
     *     [response_header_timeout: <duration> | default = 2m]
     *
     *     # If the client connects via HTTPS and this option is enabled, the client
     *     # will accept any certificate and hostname.
     *     # CLI flag: -<prefix>.swift.http.insecure-skip-verify
     *     [insecure_skip_verify: <boolean> | default = false]
     *
     *     # Maximum time to wait for a TLS handshake. 0 means no limit.
     *     # CLI flag: -<prefix>.swift.tls-handshake-timeout
     *     [tls_handshake_timeout: <duration> | default = 10s]
     *
     *     # The time to wait for a server's first response headers after fully writing
     *     # the request headers if the request has an Expect header. 0 to send the
     *     # request body immediately.
     *     # CLI flag: -<prefix>.swift.expect-continue-timeout
     *     [expect_continue_timeout: <duration> | default = 1s]
     *
     *     # Maximum number of idle (keep-alive) connections across all hosts. 0 means
     *     # no limit.
     *     # CLI flag: -<prefix>.swift.max-idle-connections
     *     [max_idle_connections: <int> | default = 100]
     *
     *     # Maximum number of idle (keep-alive) connections to keep per-host. If 0, a
     *     # built-in default value is used.
     *     # CLI flag: -<prefix>.swift.max-idle-connections-per-host
     *     [max_idle_connections_per_host: <int> | default = 100]
     *
     *     # Maximum number of connections per host. 0 means no limit.
     *     # CLI flag: -<prefix>.swift.max-connections-per-host
     *     [max_connections_per_host: <int> | default = 0]
     *
     *     # Path to the CA certificates to validate server certificate against. If not
     *     # set, the host's root CA certificates are used.
     *     # CLI flag: -<prefix>.swift.http.tls-ca-path
     *     [tls_ca_path: <string> | default = ""]
     *
     *     # Path to the client certificate, which will be used for authenticating with
     *     # the server. Also requires the key path to be configured.
     *     # CLI flag: -<prefix>.swift.http.tls-cert-path
     *     [tls_cert_path: <string> | default = ""]
     *
     *     # Path to the key for the client certificate. Also requires the client
     *     # certificate to be configured.
     *     # CLI flag: -<prefix>.swift.http.tls-key-path
     *     [tls_key_path: <string> | default = ""]
     *
     *     # Override the expected name on the server certificate.
     *     # CLI flag: -<prefix>.swift.http.tls-server-name
     *     [tls_server_name: <string> | default = ""]
     *
     * filesystem:
     *   # Local filesystem storage directory.
     *   # CLI flag: -<prefix>.filesystem.dir
     *   [dir: <string> | default = ""]
     *
     * alibaba:
     *   # Endpoint to connect to.
     *   # CLI flag: -<prefix>.oss.endpoint
     *   [endpoint: <string> | default = ""]
     *
     *   # Name of OSS bucket.
     *   # CLI flag: -<prefix>.oss.bucketname
     *   [bucket: <string> | default = ""]
     *
     *   # alibabacloud Access Key ID
     *   # CLI flag: -<prefix>.oss.access-key-id
     *   [access_key_id: <string> | default = ""]
     *
     *   # alibabacloud Secret Access Key
     *   # CLI flag: -<prefix>.oss.access-key-secret
     *   [access_key_secret: <string> | default = ""]
     *
     * bos:
     *   # Name of BOS bucket.
     *   # CLI flag: -<prefix>.bos.bucket
     *   [bucket: <string> | default = ""]
     *
     *   # BOS endpoint to connect to.
     *   # CLI flag: -<prefix>.bos.endpoint
     *   [endpoint: <string> | default = ""]
     *
     *   # Baidu Cloud Engine (BCE) Access Key ID.
     *   # CLI flag: -<prefix>.bos.access-key
     *   [access_key: <string> | default = ""]
     *
     *   # Baidu Cloud Engine (BCE) Secret Access Key.
     *   # CLI flag: -<prefix>.bos.secret-key
     *   [secret_key: <string> | default = ""]
     *
     * # Prefix for all objects stored in the backend storage. For simplicity, it may
     * # only contain digits, English alphabet letters and dashes.
     * # CLI flag: -<prefix>.storage-prefix
     * [storage_prefix: <string> | default = ""]
     */
) {
}