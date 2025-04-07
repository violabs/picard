package io.violabs.picard.domain.k8sResources.extend.deviceClass

import io.violabs.picard.domain.OpaqueDeviceConfiguration

data class DeviceClassConfiguration(
    val opaque: OpaqueDeviceConfiguration? = null
)