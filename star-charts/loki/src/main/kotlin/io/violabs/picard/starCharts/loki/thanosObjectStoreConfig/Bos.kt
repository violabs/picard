package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class Bos(
    /**
     *   # Name of BOS bucket.
     *   # CLI flag: -<prefix>.bos.bucket
     *   [bucket: <string> | default = ""]
     */
    val bucket: String? = null,
    /**
     *   # BOS endpoint to connect to.
     *   # CLI flag: -<prefix>.bos.endpoint
     *   [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     *   # Baidu Cloud Engine (BCE) Access Key ID.
     *   # CLI flag: -<prefix>.bos.access-key
     *   [access_key: <string> | default = ""]
     */
    val accessKey: String? = null,
    /**
     *   # Baidu Cloud Engine (BCE) Secret Access Key.
     *   # CLI flag: -<prefix>.bos.secret-key
     *   [secret_key: <string> | default = ""]
     */
    val secretKey: String? = null,
)