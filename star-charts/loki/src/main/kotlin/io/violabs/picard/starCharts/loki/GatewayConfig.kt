package io.violabs.picard.starCharts.loki

data class GatewayConfig(
    val service: Service
) {
    data class Service(
        val type: Type
    ) {
        enum class Type {
            LoadBalancer
        }
    }
}