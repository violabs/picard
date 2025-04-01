package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Container
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.VolumeMount

class ContainerBuilder : Builder<Container> {
    var name: String? = null
    var image: String? = null
    var command: List<String>? = null
    var restartPolicy: RestartPolicy? = null
    private var ports: List<Container.PortConfig>? = null
    private var volumeMounts: List<VolumeMount>? = null

    override fun build(): Container = Container(
        name = requireNotNull(name) { "Please provide a name" },
        image = requireNotNull(image) { "Please provide an image" },
        command = command,
        ports = ports,
        volumeMounts = volumeMounts,
        restartPolicy = restartPolicy
    )

    fun command(vararg commandParts: String) {
        this.command = commandParts.toList()
    }

    fun ports(scope: ContainerPortsBuilder.() -> Unit) {
        ports = scopedBuild(::ContainerPortsBuilder, scope)
    }

    fun volumeMounts(scope: VolumeMountsBuilder.() -> Unit) {
        volumeMounts = scopedBuild(::VolumeMountsBuilder, scope)
    }
}