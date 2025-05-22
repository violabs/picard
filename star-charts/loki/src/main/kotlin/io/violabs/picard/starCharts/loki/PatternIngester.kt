package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.SingleEntryTransformDsl

@SingleEntryTransformDsl<Boolean>(inputType = Boolean::class)
data class PatternIngester(
    val enabled: Boolean
)