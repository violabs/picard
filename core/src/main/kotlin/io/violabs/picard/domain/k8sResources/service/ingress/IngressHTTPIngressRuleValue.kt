package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.DSLBuilder

data class IngressHTTPIngressRuleValue(
    val paths: List<IngressHTTPIngressPath>
) {
    class Builder : DSLBuilder<IngressHTTPIngressRuleValue> {
        private var paths: List<IngressHTTPIngressPath>? = null

        fun paths(scope: IngressHTTPIngressPath.Group.() -> Unit) {
            paths = IngressHTTPIngressPath.Group().apply(scope).paths()
        }

        override fun build(): IngressHTTPIngressRuleValue {
            return IngressHTTPIngressRuleValue(
                paths = vRequireNotEmpty(this::paths)
            )
        }
    }
}