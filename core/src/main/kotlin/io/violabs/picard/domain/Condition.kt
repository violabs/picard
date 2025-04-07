package io.violabs.picard.domain

import java.time.LocalDateTime

data class Condition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
) : BaseCondition

data class NodeCondition(
    val status: BooleanType,
    val type: String,
    val lastProbeTime: LocalDateTime? = null,
    val lastTransitionTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
) : BaseCondition

data class ServiceCondition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null,
    val observedGeneration: Long? = null
) : BaseCondition

data class ComponentCondition(
    val status: BooleanType,
    val type: String,
    val message: String? = null,
    val reason: String? = null
) : BaseCondition

data class SigningRequestCondition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val lastUpdateTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
)