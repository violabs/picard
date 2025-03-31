package io.violabs.picard.domain

import java.time.LocalDateTime

data class PodCondition(
    val type: String,
    val status: String,
    val lastTransitionTime: LocalDateTime,
    val lastProbeTime: LocalDateTime? = null
)