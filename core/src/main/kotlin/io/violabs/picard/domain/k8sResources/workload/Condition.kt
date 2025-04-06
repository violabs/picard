package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

data class Condition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
)