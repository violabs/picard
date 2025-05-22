package io.violabs.picard.generateTest

import io.violabs.picard.metaDsl.annotation.SingleEntryTransformDsl

@SingleEntryTransformDsl<String>(String::class)
data class Stardate(val content: String)