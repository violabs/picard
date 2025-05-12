package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.SingleEntryDSL

/**
 * todo: Restrict format
 */
@SingleEntryDSL<String, String>(
    paramType = String::class,
    returnType = String::class
)
data class Duration(val amount: String)