package io.violabs.picard.domain.k8sResources.workload.pod.action

import io.violabs.picard.domain.DslBuilder

data class GRPCAction(
    val port: Int,
    val service: String? = null
) {
    class Builder : DslBuilder<GRPCAction> {
        var port: Int? = null
        var service: String? = null

        override fun build(): GRPCAction {
            return GRPCAction(
                port = requireNotNull(port) { "port is required" },
                service = service
            )
        }
    }
}