package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Container


class PortBuilder : Builder<Container.Port> {
    var containerPort: Int? = null

    override fun build(): Container.Port = Container.Port(
        containerPort = requireNotNull(containerPort) { "Please provide a container port" }
    )
}