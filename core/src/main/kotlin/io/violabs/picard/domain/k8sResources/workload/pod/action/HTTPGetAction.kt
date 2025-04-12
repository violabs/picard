package io.violabs.picard.domain.k8sResources.workload.pod.action

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.IntOrString

data class HTTPGetAction(
    val port: IntOrString,
    val host: String? = null,
    val httpHeaders: List<HttpHeader>? = null,
    val path: String? = null,
    val scheme: String? = null
) {
    class Builder() : DslBuilder<HTTPGetAction> {
        private var port: IntOrString? = null
        private var httpHeaders: MutableList<HttpHeader>? = null
        var host: String? = null
        var path: String? = null
        var scheme: String? = null

        fun port(port: Int) {
            this.port = IntOrString(port)
        }

        fun port(port: String) {
            this.port = IntOrString(str = port)
        }

        fun httpHeaders(scope: HttpHeaderGroup.() -> Unit) {
            httpHeaders = HttpHeaderGroup().apply(scope).headers()
        }

        override fun build(): HTTPGetAction {
            return HTTPGetAction(
                vRequireNotNull(this::port),
                host,
                httpHeaders,
                path,
                scheme
            )
        }

        class HttpHeaderGroup {
            private var httpHeaders: MutableList<HttpHeader> = mutableListOf()

            fun headers(): MutableList<HttpHeader>? {
                return httpHeaders
            }

            fun header(name: String, value: String) {
                httpHeaders.add(HttpHeader(name, value))
            }
        }
    }
}