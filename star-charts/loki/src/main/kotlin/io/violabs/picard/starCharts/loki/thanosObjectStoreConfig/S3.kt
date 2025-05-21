package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.picard.starCharts.loki.Sse

data class S3(
    /**
     *   # The S3 bucket endpoint. It could be an AWS S3 endpoint listed at
     *   # https://docs.aws.amazon.com/general/latest/gr/s3.html or the address of an
     *   # S3-compatible service in hostname:port format.
     *   # CLI flag: -<prefix>.s3.endpoint
     *   [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     *   # S3 region. If unset, the client will issue a S3 GetBucketLocation API call
     *   # to autodetect it.
     *   # CLI flag: -<prefix>.s3.region
     *   [region: <string> | default = ""]
     */
    val region: String? = null,
    /**
     *   # S3 bucket name
     *   # CLI flag: -<prefix>.s3.bucket-name
     *   [bucket_name: <string> | default = ""]
     */
    val bucketName: String? = null,
    /**
     *   # S3 secret access key
     *   # CLI flag: -<prefix>.s3.secret-access-key
     *   [secret_access_key: <string> | default = ""]
     */
    val secretAccessKey: String? = null,
    /**
     *   # S3 access key ID
     *   # CLI flag: -<prefix>.s3.access-key-id
     *   [access_key_id: <string> | default = ""]
     */
    val accessKeyId: String? = null,
    /**
     *   # S3 session token
     *   # CLI flag: -<prefix>.s3.session-token
     *   [session_token: <string> | default = ""]
     */
    val sessionToken: String? = null,
    /**
     *   # If enabled, use http:// for the S3 endpoint instead of https://. This could
     *   # be useful in local dev/test environments while using an S3-compatible
     *   # backend storage, like Minio.
     *   # CLI flag: -<prefix>.s3.insecure
     *   [insecure: <boolean> | default = false]
     */
    val insecure: Boolean = false,
    /**
     *   # Use a specific version of the S3 list object API. Supported values are v1 or
     *   # v2. Default is unset.
     *   # CLI flag: -<prefix>.s3.list-objects-version
     *   [list_objects_version: <string> | default = ""]
     */
    val listObjectsVersion: String? = null,
    /**
     *   # Bucket lookup style type, used to access bucket in S3-compatible service.
     *   # Default is auto. Supported values are: auto, path, virtual-hosted.
     *   # CLI flag: -<prefix>.s3.bucket-lookup-type
     *   [bucket_lookup_type: <int> | default = auto]
     */
    val bucketLookupType: Int? = null,
    /**
     *   # When enabled, direct all AWS S3 requests to the dual-stack IPv4/IPv6
     *   # endpoint for the configured region.
     *   # CLI flag: -<prefix>.s3.dualstack-enabled
     *   [dualstack_enabled: <boolean> | default = true]
     */
    val dualstackEnabled: Boolean? = null,
    /**
     *   # The S3 storage class to use, not set by default. Details can be found at
     *   # https://aws.amazon.com/s3/storage-classes/. Supported values are: STANDARD,
     *   # REDUCED_REDUNDANCY, GLACIER, STANDARD_IA, ONEZONE_IA, INTELLIGENT_TIERING,
     *   # DEEP_ARCHIVE, OUTPOSTS, GLACIER_IR, SNOW, EXPRESS_ONEZONE
     *   # CLI flag: -<prefix>.s3.storage-class
     *   [storage_class: <string> | default = ""]
     */
    val storageClass: String? = null,
    /**
     *   # If enabled, it will use the default authentication methods of the AWS SDK
     *   # for go based on known environment variables and known AWS config files.
     *   # CLI flag: -<prefix>.s3.native-aws-auth-enabled
     *   [native_aws_auth_enabled: <boolean> | default = false]
     */
    val nativeAwsAuthEnabled: Boolean? = null,
    /**
     *   # The minimum file size in bytes used for multipart uploads. If 0, the value
     *   # is optimally computed for each object.
     *   # CLI flag: -<prefix>.s3.part-size
     *   [part_size: <int> | default = 0]
     */
    val partSize: Int? = null,
    /**
     *   # If enabled, a Content-MD5 header is sent with S3 Put Object requests.
     *   # Consumes more resources to compute the MD5, but may improve compatibility
     *   # with object storage services that do not support checksums.
     *   # CLI flag: -<prefix>.s3.send-content-md5
     *   [send_content_md5: <boolean> | default = false]
     */
    val sendContentMd5: Boolean? = null,
    /**
     *   # Accessing S3 resources using temporary, secure credentials provided by AWS
     *   # Security Token Service.
     *   # CLI flag: -<prefix>.s3.sts-endpoint
     *   [sts_endpoint: <string> | default = ""]
     */
    val stsEndpoint: String? = null,
    /**
     *   # The maximum number of retries for S3 requests that are retryable. Default is
     *   # 10, set this to 1 to disable retries.
     *   # CLI flag: -<prefix>.s3.max-retries
     *   [max_retries: <int> | default = 10]
     */
    val maxRetries: Int? = null,
    val sse: Sse? = null,
    val http: HttpConfig? = null,
    val trace: Trace? = null,
)