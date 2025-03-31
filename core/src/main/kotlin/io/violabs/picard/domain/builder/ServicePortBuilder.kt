package io.violabs.picard.domain.builder

import io.violabs.picard.domain.ServicePortConfig
import io.violabs.picard.domain.ServicePortConfig.Protocol


class ServicePortBuilder : Builder<ServicePortConfig> {
    var protocol: Protocol? = null
    var port: Int? = null
    var targetPort: Int? = null

    override fun build(): ServicePortConfig = ServicePortConfig(
        protocol = requireNotNull(protocol) { "Please provide a protocol" },
        port = requireNotNull(port) { "Please provide a port" },
        targetPort = requireNotNull(targetPort) { "Please provide a target port" }
    )
}