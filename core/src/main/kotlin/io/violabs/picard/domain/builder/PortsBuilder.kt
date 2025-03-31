package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Container
import io.violabs.picard.scopedBuild

class PortsBuilder : Builder<List<Container.Port>> {
    private val ports = mutableListOf<Container.Port>()

    override fun build(): List<Container.Port> = ports

    fun port(scope: PortBuilder.() -> Unit) {
        ports.add(scopedBuild(::PortBuilder, scope))
    }
}