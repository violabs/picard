package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.DSLBuilder

data class IngressBackend(
    val resource: TypedLocalObjectReference? = null,
    val service: IngressServiceBackend? = null
) {
    class Builder : DSLBuilder<IngressBackend> {
        private var resource: TypedLocalObjectReference? = null
        private var service: IngressServiceBackend? = null

        fun resource(block: TypedLocalObjectReference.Builder.() -> Unit) {
            resource = TypedLocalObjectReference.Builder().apply(block).build()
        }

        fun service(block: IngressServiceBackend.Builder.() -> Unit) {
            service = IngressServiceBackend.Builder().apply(block).build()
        }

        override fun build(): IngressBackend {
            return IngressBackend(
                resource = resource,
                service = service
            )
        }
    }
}