package io.violabs.picard.domain

import java.time.LocalDateTime

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
                status = requireNotNull(status),
                type = requireNotNull(type),
                lastTransitionTime = lastTransitionTime,
                message = message,
                reason = reason
            )
        }
    }
}

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
                status = requireNotNull(status),
                type = requireNotNull(type),
                lastProbeTime = lastProbeTime,
                lastTransitionTime = lastTransitionTime,
                message = message,
                reason = reason
            )
        }
    }
}

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
                status = requireNotNull(status),
                type = requireNotNull(type),
                lastTransitionTime = lastTransitionTime,
                message = message,
                reason = reason,
                observedGeneration = observedGeneration
            )
        }
    }
}

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
                status = requireNotNull(status),
                type = requireNotNull(type),
                message = message,
                reason = reason,
            )
        }
    }
}

data class SigningRequestCondition(
    val status: BooleanType,
    val type: String,
    val lastTransitionTime: LocalDateTime? = null,
    val lastUpdateTime: LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null
) {
    class Builder : DslBuilder<SigningRequestCondition> {
        var status: BooleanType? = null
        var type: String? = null
        var lastTransitionTime: LocalDateTime? = null
        var lastUpdateTime: LocalDateTime? = null
        var message: String? = null
        var reason: String? = null
        override fun build(): SigningRequestCondition {
            return SigningRequestCondition(
                status = requireNotNull(status),
                type = requireNotNull(type),
                lastTransitionTime = lastTransitionTime,
                lastUpdateTime = lastUpdateTime,
                message = message,
                reason = reason
            )
        }
    }
}