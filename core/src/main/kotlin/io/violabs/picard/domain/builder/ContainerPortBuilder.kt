package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Container


class ContainerPortBuilder : Builder<Container.PortConfig> {
    var containerPort: Int? = null

    override fun build(): Container.PortConfig = Container.PortConfig(
        containerPort = requireNotNull(containerPort) { "Please provide a container port" }
    )
}