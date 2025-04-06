package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

data class Condition(
    override val status: BooleanType,
    override val type: String,
    override val lastTransitionTime: LocalDateTime? = null,
    override val message: String? = null,
    override val reason: String? = null
) : BaseCondition

data class JobCondition(
    override val status: BooleanType,
    override val type: String,
    val lastProbeTime: LocalDateTime? = null,
    override val lastTransitionTime: LocalDateTime? = null,
    override val message: String? = null,
    override val reason: String? = null
) : BaseCondition

data class ServiceCondition(
    override val status: BooleanType,
    override val type: String,
    override val lastTransitionTime: LocalDateTime? = null,
    override val message: String? = null,
    override val reason: String? = null,
    val observedGeneration: Long? = null
) : BaseCondition