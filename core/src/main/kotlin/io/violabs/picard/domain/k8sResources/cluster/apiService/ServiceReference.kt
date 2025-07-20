package io.violabs.picard.domain.k8sResources.cluster.apiService

import io.violabs.picard.domain.BaseServiceReference
import io.violabs.picard.common.DslBuilder

data class ServiceReference(
    val name: String? = null,
    val namespace: String? = null,
    val port: Int? = null
) : BaseServiceReference {
    class Builder : DslBuilder<ServiceReference> {
        var name: String? = null
        var namespace: String? = null
        var port: Int? = null

        override fun build(): ServiceReference {
            return ServiceReference(
                name = name,
                namespace = namespace,
                port = port
            )
        }
    }
}