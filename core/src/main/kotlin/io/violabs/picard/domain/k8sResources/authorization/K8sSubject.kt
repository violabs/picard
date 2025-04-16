package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSubject
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class K8sSubject(
    val kind: String,
    val name: String,
    val apiGroup: String? = null,
    val namespace: String? = null
) : BaseSubject {
    class Builder : DSLBuilder<K8sSubject> {
        var kind: String? = null
        var name: String? = null
        var apiGroup: String? = null
        var namespace: String? = null

        override fun build(): K8sSubject {
            return K8sSubject(
                kind = vRequireNotNull(this::kind),
                name = vRequireNotNull(this::name),
                apiGroup = apiGroup,
                namespace = namespace
            )
        }
    }

    class Group : BuilderGroup<K8sSubject, Builder>(Builder()) {
        fun subjects(): List<K8sSubject>? = items()

        fun subject(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}
