package io.violabs.picard.domain.builder

import io.violabs.picard.domain.ServicePortConfig

class ServicePortsBuilder : Builder<List<ServicePortConfig>> {
    private val ports = mutableListOf<ServicePortConfig>()

    override fun build(): List<ServicePortConfig> = ports

    fun port(scope: ServicePortBuilder.() -> Unit) {
        ports.add(scopedBuild(::ServicePortBuilder, scope))
    }
}