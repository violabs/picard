package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class Alibaba(
    /**
     *   # Endpoint to connect to.
     *   # CLI flag: -<prefix>.oss.endpoint
     *   [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     *   # Name of OSS bucket.
     *   # CLI flag: -<prefix>.oss.bucketname
     *   [bucket: <string> | default = ""]
     */
    val bucket: String? = null,
    /**
     *   # alibabacloud Access Key ID
     *   # CLI flag: -<prefix>.oss.access-key-id
     *   [access_key_id: <string> | default = ""]
     */
    val accessKeyId: String? = null,
    /**
     *   # alibabacloud Secret Access Key
     *   # CLI flag: -<prefix>.oss.access-key-secret
     *   [access_key_secret: <string> | default = ""]
     */
    val accessKeySecret: String? = null
)