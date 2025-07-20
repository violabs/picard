package io.violabs.picard.domain.condition

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseCondition
import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

typealias StandardConditionGroup = ConditionGroup<Condition, Condition.Builder>

data class Condition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
) : BaseCondition {
    class Builder : DslBuilder<Condition> {
        var status: BooleanType? = null
        var type: String? = null
        var lastTransitionTime: LocalDateTime? = null
        var message: String? = null
        var reason: String? = null
        override fun build(): Condition {
            return Condition(
                status = vRequireNotNull(this::status),
                type = vRequireNotNull(this::type),
                lastTransitionTime = lastTransitionTime,
                message = message,
                reason = reason
            )
        }
    }

    companion object {
        fun group(scope: StandardConditionGroup.() -> Unit): List<Condition>? {
            return StandardConditionGroup(Builder()).apply(scope).conditions()
        }
    }
}

typealias NodeConditionGroup = ConditionGroup<NodeCondition, NodeCondition.Builder>

data class NodeCondition(
    val status: BooleanType,
    val type: String,
    val lastProbeTime: LocalDateTime? = null,
    val lastTransitionTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
) : BaseCondition {
    class Builder : DslBuilder<NodeCondition> {
        var status: BooleanType? = null
        var type: String? = null
        var lastProbeTime: LocalDateTime? = null
        var lastTransitionTime: LocalDateTime? = null
        var message: String? = null
        var reason: String? = null
        override fun build(): NodeCondition {
            return NodeCondition(
                status = vRequireNotNull(this::status),
                type = vRequireNotNull(this::type),
                lastProbeTime = lastProbeTime,
                lastTransitionTime = lastTransitionTime,
                message = message,
                reason = reason
            )
        }
    }

    companion object {
        fun group(scope: NodeConditionGroup.() -> Unit): List<NodeCondition>? {
            return NodeConditionGroup(Builder()).apply(scope).conditions()
        }
    }
}

typealias ServiceConditionGroup = ConditionGroup<ServiceCondition, ServiceCondition.Builder>

data class ServiceCondition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null,
    val observedGeneration: Long? = null
) : BaseCondition {
    class Builder : DslBuilder<ServiceCondition> {
        var status: BooleanType? = null
        var type: String? = null
        var lastTransitionTime: LocalDateTime? = null
        var message: String? = null
        var reason: String? = null
        var observedGeneration: Long? = null
        override fun build(): ServiceCondition {
            return ServiceCondition(
                status = vRequireNotNull(this::status),
                type = vRequireNotNull(this::type),
                lastTransitionTime = lastTransitionTime,
                message = message,
                reason = reason,
                observedGeneration = observedGeneration
            )
        }
    }

    companion object {
        fun group(scope: ServiceConditionGroup.() -> Unit): List<ServiceCondition>? {
            return ServiceConditionGroup(Builder()).apply(scope).conditions()
        }
    }
}

typealias ComponentConditionGroup = ConditionGroup<ComponentCondition, ComponentCondition.Builder>

data class ComponentCondition(
    val status: BooleanType,
    val type: String,
    val message: String? = null,
    val reason: String? = null
) : BaseCondition {
    class Builder : DslBuilder<ComponentCondition> {
        var status: BooleanType? = null
        var type: String? = null
        var message: String? = null
        var reason: String? = null
        override fun build(): ComponentCondition {
            return ComponentCondition(
                status = vRequireNotNull(this::status),
                type = vRequireNotNull(this::type),
                message = message,
                reason = reason,
            )
        }
    }

    companion object {
        fun group(scope: ComponentConditionGroup.() -> Unit): List<ComponentCondition>? {
            return _root_ide_package_.io.violabs.picard.domain.condition.ComponentConditionGroup(Builder()).apply(scope).conditions()
        }
    }
}

typealias SigningRequestConditionGroup = ConditionGroup<SigningRequestCondition, SigningRequestCondition.Builder>

data class SigningRequestCondition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val lastUpdateTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
) : BaseCondition {
    class Builder : DslBuilder<SigningRequestCondition> {
        var status: BooleanType? = null
        var type: String? = null
        var lastTransitionTime: LocalDateTime? = null
        var lastUpdateTime: LocalDateTime? = null
        var message: String? = null
        var reason: String? = null
        override fun build(): SigningRequestCondition {
            return SigningRequestCondition(
                status = vRequireNotNull(this::status),
                type = vRequireNotNull(this::type),
                lastTransitionTime = lastTransitionTime,
                lastUpdateTime = lastUpdateTime,
                message = message,
                reason = reason
            )
        }
    }

    companion object {
        fun group(scope: SigningRequestConditionGroup.() -> Unit): List<SigningRequestCondition>? {
            return SigningRequestConditionGroup(Builder()).apply(scope).conditions()
        }
    }
}