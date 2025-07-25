package io.violabs.picard.domain.k8sResources.service.endpoint

import io.violabs.picard.common.DslBuilder

data class EndpointHints(
    val forZones: List<ForZone>? = null
) {
    data class ForZone(val name: String)

    class Builder : DslBuilder<EndpointHints> {
        private var forZones: List<ForZone>? = null

        fun forZones(vararg zones: String) {
            forZones = zones.map { ForZone(it) }
        }

        override fun build(): EndpointHints {
            return EndpointHints(
                forZones = forZones
            )
        }
    }
}