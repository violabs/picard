package io.violabs.picard.starCharts.loki.minioConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class MinioConfig(
    val address: String? = null,
    val buckets: List<MinioBucket>? = null,
    val drivesPerNode: Int? = null,
    val enabled: Boolean? = null,
    val persistence: MinioPersistence? = null,
    val replicas: Int? = null,
    val resources: MinioResources? = null,
    val rootPassword: String? = null,
    val rootUser: String? = null,
    val users: List<MinioUser>? = null
)