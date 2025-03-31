package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Container

class ContainerBuilder : Builder<Container> {
    var name: String? = null
    var image: String? = null
    var command: List<String>? = null
    private var ports: List<Container.Port>? = null

    override fun build(): Container = Container(
        name = requireNotNull(name) { "Please provide a name" },
        image = requireNotNull(image) { "Please provide an image" },
        command = command,
        ports = ports
    )

    fun ports(scope: PortsBuilder.() -> Unit) {
        ports = scopedBuild(::PortsBuilder, scope)
    }
}