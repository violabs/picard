package io.violabs.picard.generateTest

import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL

@SingleEntryTransformDSL<String>(String::class)
data class Stardate(val content: String)