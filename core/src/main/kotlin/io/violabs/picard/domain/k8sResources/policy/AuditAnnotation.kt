package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

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

        fun addAuditAnnotation(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}