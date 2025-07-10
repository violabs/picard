package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.domain.BasePort
import io.violabs.picard.common.DslBuilder

data class IngressServiceBackendPort(
    val name: String? = null,
    val number: Int? = null
) : BasePort {
    class Builder : DslBuilder<IngressServiceBackendPort> {
        var name: String? = null
        var number: Int? = null

        override fun build(): IngressServiceBackendPort {
            return IngressServiceBackendPort(
                name = name,
                number = number
            )
        }
    }
}