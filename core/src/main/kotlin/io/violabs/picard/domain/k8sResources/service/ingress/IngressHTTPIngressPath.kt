package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class IngressHTTPIngressPath(
    val backend: IngressBackend,
    val pathType: String,
    val path: String? = null
) {
    class Builder : DSLBuilder<IngressHTTPIngressPath> {
        private var backend: IngressBackend? = null
        var pathType: String? = null
        var path: String? = null

        fun backend(scope: IngressBackend.Builder.() -> Unit) {
            backend = IngressBackend.Builder().apply(scope).build()
        }

        override fun build(): IngressHTTPIngressPath {
            return IngressHTTPIngressPath(
                backend = vRequireNotNull(this::backend),
                pathType = vRequireNotNull(this::pathType),
                path = path
            )
        }
    }

    class Group : BuilderGroup<IngressHTTPIngressPath, Builder>(Builder()) {
        fun paths(): List<IngressHTTPIngressPath>? = items()

        fun path(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}