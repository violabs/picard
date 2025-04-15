package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.BaseSubject
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
                kind = requireNotNull(kind),
                name = requireNotNull(name),
                apiGroup = apiGroup,
                namespace = namespace
            )
        }
    }
}
