package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.validation.RangeLimit

data class ContainerPort(
    @RangeLimit(1, 65535, required = true)
    val containerPort: Int,
    val hostIp: String? = null,
    @RangeLimit(1, 65535, required = true)
    val hostPort: Int? = null,
    val name: String? = null,
    val protocol: Protocol? = null
) {
    class Builder : DSLBuilder<ContainerPort> {
        var containerPort: Int? = null
        var hostIp: String? = null
        var hostPort: Int? = null
        var name: String? = null
        var protocol: Protocol? = null

        override fun build(): ContainerPort {
            return ContainerPort(
                vRequireNotNull(this::containerPort),
                hostIp,
                hostPort,
                name,
                protocol
            )
        }
    }
}