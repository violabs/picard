package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class AuditAnnotation(
    val key: String,
    val valueExpression: String
) {
    class Builder : DSLBuilder<AuditAnnotation> {
        var key: String? = null
        var valueExpression: String? = null

        override fun build(): AuditAnnotation {
            return AuditAnnotation(
                key = vRequireNotNull(this::key),
                valueExpression = vRequireNotNull(this::valueExpression)
            )
        }
    }

    class Group : BuilderGroup<AuditAnnotation, Builder>(Builder()) {
        fun annotations(): List<AuditAnnotation>? = items()

        fun annotation(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}