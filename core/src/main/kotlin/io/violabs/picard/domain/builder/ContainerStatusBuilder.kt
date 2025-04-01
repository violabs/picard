package io.violabs.picard.domain.builder

import io.violabs.picard.domain.ContainerStatus

class ContainerStatusBuilder : Builder<ContainerStatus> {
    var containerId: String? = null
    private var ready: Boolean = false

    override fun build(): ContainerStatus {
        return ContainerStatus(
            containerId = requireNotNull(containerId) { "containerId cannot be null" },
            ready = ready
        )
    }

    fun isReady() {
        ready = true
    }
}