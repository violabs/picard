package io.violabs.picard.starCharts.loki.minioConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Quantity

@GeneratedDsl
data class MinioResourceRequests(
    val cpu: Quantity? = null,
    val memory: Quantity? = null
)