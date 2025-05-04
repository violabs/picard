package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class Validation(
    val expression: String,
    val message: String? = null,
    val messageExpression: String? = null,
    val reason: String? = null
) {
    class Builder : DSLBuilder<Validation> {
        var expression: String? = null
        var message: String? = null
        var messageExpression: String? = null
        var reason: String? = null

        override fun build(): Validation {
            return Validation(
                expression = vRequireNotNull(this::expression),
                message = message,
                messageExpression = messageExpression,
                reason = reason
            )
        }
    }

    class Group : BuilderGroup<Validation, Builder>(Builder()) {
        fun validations(): List<Validation>? = items()

        fun validation(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}