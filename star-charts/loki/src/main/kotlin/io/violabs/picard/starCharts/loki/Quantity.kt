package io.violabs.picard.starCharts.loki

import io.violabs.konstellation.metaDsl.annotation.SingleEntryTransformDsl


@JvmInline
@SingleEntryTransformDsl<String>(String::class)
value class Quantity(val value: String)