package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class IngressRule(
    val host: String? = null,
    val http: IngressHTTPIngressRuleValue? = null
) {
    class Builder : DSLBuilder<IngressRule> {
        var host: String? = null
        private var http: IngressHTTPIngressRuleValue? = null

        fun http(block: IngressHTTPIngressRuleValue.Builder.() -> Unit) {
            http = IngressHTTPIngressRuleValue.Builder().apply(block).build()
        }

        override fun build(): IngressRule {
            return IngressRule(
                host = host,
                http = http
            )
        }
    }

    class Group : BuilderGroup<IngressRule, Builder>(Builder()) {
        fun rules(): List<IngressRule>? = items()

        fun addIngressRule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}