package io.violabs.picard.domain.builder

import io.violabs.picard.domain.k8sResources.workload.pod.container.Container


class ContainerPortBuilder : Builder<Container.Port> {
    var containerPort: Int? = null

    override fun build(): Container.Port = Container.Port(
        containerPort = requireNotNull(containerPort) { "Please provide a container port" }
    )
}