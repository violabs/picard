package io.violabs.picard.domain.k8sResources.extend.json

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class ValidationRule(
    val rule: String,
    val fieldPath: String? = null,
    val message: String? = null,
    val messageExpression: String? = null,
    val optionalOldSelf: Boolean? = null,
    val reason: String? = null
) {
    class Builder : DSLBuilder<ValidationRule> {
        var rule: String? = null
        var fieldPath: String? = null
        var message: String? = null
        var messageExpression: String? = null
        private var optionalOldSelf: Boolean? = null
        var reason: String? = null

        fun optionalOldSelf(value: Boolean = true) {
            this.optionalOldSelf = value
        }

        override fun build(): ValidationRule {
            return ValidationRule(
                rule = vRequireNotNull(this::rule),
                fieldPath = fieldPath,
                message = message,
                messageExpression = messageExpression,
                optionalOldSelf = optionalOldSelf,
                reason = reason
            )
        }
    }

    class Group : BuilderGroup<ValidationRule, Builder>(Builder()) {
        fun rules(): List<ValidationRule>? = items()

        fun rule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}