package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Container

class ContainerPortsBuilder : Builder<List<Container.PortConfig>> {
    private val ports = mutableListOf<Container.PortConfig>()

    override fun build(): List<Container.PortConfig> = ports

    fun port(scope: ContainerPortBuilder.() -> Unit) {
        ports.add(scopedBuild(::ContainerPortBuilder, scope))
    }
}