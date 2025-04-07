package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BooleanType
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
)