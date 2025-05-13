package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL

/**
 * todo: Restrict format
 */
@SingleEntryTransformDSL<String>(String::class)
data class Duration(val amount: String)