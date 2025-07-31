package io.violabs.picard.v2.resources.resource.slice

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class CounterSet(
    val counters: Map<String, Counter>? = null,
    val name: String
)