package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL

@SingleEntryTransformDSL<Boolean>(inputType = Boolean::class)
data class PatternIngester(
    val enabled: Boolean
)