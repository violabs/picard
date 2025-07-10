package io.violabs.picard.domain

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotNull

data class OpaqueDeviceConfiguration(
    val driver: String,
    val parameters: Any
) {
    class Builder : DslBuilder<OpaqueDeviceConfiguration> {
        var driver: String? = null
        var parameters: Any? = null

        override fun build(): OpaqueDeviceConfiguration {
            return OpaqueDeviceConfiguration(
                driver = vRequireNotNull(this::driver),
                parameters = vRequireNotNull(this::parameters)
            )
        }
    }
}