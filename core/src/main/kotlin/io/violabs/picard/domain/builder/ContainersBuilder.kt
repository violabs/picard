package io.violabs.picard.domain.builder

import io.violabs.picard.domain.k8sResources.workload.pod.container.Container

class ContainersBuilder : Builder<List<Container>> {
    private val containers = mutableListOf<Container>()

    override fun build(): List<Container> = containers

    fun container(scope: ContainerBuilder.() -> Unit) {
        containers.add(scopedBuild(::ContainerBuilder, scope))
    }
}