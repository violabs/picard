package io.violabs.picard.starCharts.loki.gatewayConfig

import io.violabs.picard.metaDsl.annotation.SingleEntryTransformDsl

@SingleEntryTransformDsl<Service.Type>(inputType = Service.Type::class)
data class Service(
    val type: Type
) {
    enum class Type {
        LoadBalancer
    }
}