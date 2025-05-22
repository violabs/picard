package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.SingleEntryTransformDsl

@SingleEntryTransformDsl<Boolean>(inputType = Boolean::class)
data class PatternIngester(
    val enabled: Boolean
)