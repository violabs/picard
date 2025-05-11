package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AWSStorageConfig(
    /**
     * # The s3_storage_config block configures the connection to Amazon S3 object
     * # storage backend.
     * [<s3_storage_config>]
     */
    val s3StorageConfig: S3StorageConfig? = null
)