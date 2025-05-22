package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.SingleEntryTransformDsl

/**
 * todo: Restrict format
 */
@SingleEntryTransformDsl<String>(String::class)
data class Duration(val amount: String)