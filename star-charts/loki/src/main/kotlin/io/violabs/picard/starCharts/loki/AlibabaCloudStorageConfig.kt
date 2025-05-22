package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.GeneratedDsl


@GeneratedDsl(
    withMapGroup = "SINGLE"
)
data class AlibabaCloudStorageConfig(
    /**
     *  Name of OSS bucket.
     *  CLI flag: -<prefix>.oss.bucketname
     *  [bucket: <string> | default = ""]
     */
    val bucket: String? = null,
    /**
     * oss Endpoint to connect to.
     * CLI flag: -<prefix>.oss.endpoint
     * [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     * alibabacloud Access Key ID
     * CLI flag: -<prefix>.oss.access-key-id
     * [access_key_id: <string> | default = ""]
     */
    val accessKeyId: String? = null,
    /**
     * alibabacloud Secret Access Key
     * CLI flag: -<prefix>.oss.secret-access-key
     * [secret_access_key: <string> | default = ""]
     */
    val secretAccessKey: String? = null,
    /**
     * Connection timeout in seconds
     * CLI flag: -<prefix>.oss.conn-timeout-sec
     * [conn_timeout_sec: <int> | default = 30]
     */
    val connTimeoutSec: Int? = null,
    /**
     * Read/Write timeout in seconds
     * CLI flag: -<prefix>.oss.read-write-timeout-sec
     * [read_write_timeout_sec: <int> | default = 60]
     */
    val readWriteTimeoutSec: Int? = null
)