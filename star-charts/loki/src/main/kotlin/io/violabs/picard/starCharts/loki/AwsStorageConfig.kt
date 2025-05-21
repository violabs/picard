package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.s3StorageConfig.S3StorageConfig

@GeneratedDSL(
    withMapGroup = "SINGLE"
)
data class AwsStorageConfig(
    /**
     * # The s3_storage_config block configures the connection to Amazon S3 object
     * # storage backend.
     * [<s3_storage_config>]
     */
    val s3StorageConfig: S3StorageConfig? = null
)