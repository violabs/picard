package io.violabs.picard.domain.builder

import io.violabs.picard.domain.k8sResources.pod.Container

class ContainerPortsBuilder : Builder<List<Container.Port>> {
    private val ports = mutableListOf<Container.Port>()

    override fun build(): List<Container.Port> = ports

    fun port(scope: ContainerPortBuilder.() -> Unit) {
        ports.add(scopedBuild(::ContainerPortBuilder, scope))
    }
}