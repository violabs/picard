package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class ThanosObjectStoreConfig(
    val s3: S3? = null,
    val gcs: Gcs? = null,
    val azure: Azure? = null,
    val swift: Swift? = null,
    val fileSystem: FileSystem? = null,
    val alibaba: Alibaba? = null,
    val bos: Bos? = null,
    /**
     * # Prefix for all objects stored in the backend storage. For simplicity, it may
     * # only contain digits, English alphabet letters and dashes.
     * # CLI flag: -<prefix>.storage-prefix
     * [storage_prefix: <string> | default = ""]
     */
    val storagePrefix: String? = null
)