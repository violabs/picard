package io.violabs.picard.domain.k8sResources.policy.subject

import io.violabs.picard.domain.BaseSubject
import io.violabs.picard.domain.DSLBuilder

data class ServiceAccountSubject(
    val name: String,
    val namespace: String
) : BaseSubject {
    class Builder : DSLBuilder<ServiceAccountSubject> {
        var name: String? = null
        var namespace: String? = null

        override fun build(): ServiceAccountSubject {
            return ServiceAccountSubject(
                name = requireNotNull(name),
                namespace = requireNotNull(namespace)
            )
        }
    }
}
