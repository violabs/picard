package io.violabs.picard.starCharts.loki.minioConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(
    withListGroup = true
)
data class MinioUser(
    val accessKey: String? = null,
    val policy: String? = null,
    val secretKey: String? = null,
)