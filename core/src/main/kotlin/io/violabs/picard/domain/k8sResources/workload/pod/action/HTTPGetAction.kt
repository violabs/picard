package io.violabs.picard.domain.k8sResources.workload.pod.action

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
        var host: String? = null
        private var httpHeaders: List<HttpHeader>? = null
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
                requireNotNull(port) { "Port must be specified" },
                host,
                httpHeaders,
                path,
                scheme
            )
        }

        class HttpHeaderGroup {
            private var httpHeaders: MutableList<HttpHeader>? = null

            fun headers(): List<HttpHeader>? {
                return httpHeaders
            }

            fun header(name: String, value: String) {
                if (httpHeaders == null) {
                    httpHeaders = mutableListOf()
                }

                httpHeaders!!.add(HttpHeader(name, value))
            }
        }
    }
}