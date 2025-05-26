package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.SingleEntryTransformDsl

@SingleEntryTransformDsl<String>(String::class)
data class Stardate(val content: String)