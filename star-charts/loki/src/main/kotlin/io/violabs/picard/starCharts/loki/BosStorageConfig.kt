package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class BosStorageConfig(
    /**
     * # Name of BOS bucket.
     * # CLI flag: -<prefix>.bos.bucket-name
     * [bucket_name: <string> | default = ""]
     */
    val bucketName: String? = null,
    /**
     * # BOS endpoint to connect to.
     * # CLI flag: -<prefix>.bos.endpoint
     * [endpoint: <string> | default = "bj.bcebos.com"]
     */
    val endpoint: String? = null,
    /**
     * # Baidu Cloud Engine (BCE) Access Key ID.
     * # CLI flag: -<prefix>.bos.access-key-id
     * [access_key_id: <string> | default = ""]
     */
    val accessKeyId: String? = null,
    /**
     * # Baidu Cloud Engine (BCE) Secret Access Key.
     * # CLI flag: -<prefix>.bos.secret-access-key
     * [secret_access_key: <string> | default = ""]
     */
    val secretAccessKey: String? = null,
)
