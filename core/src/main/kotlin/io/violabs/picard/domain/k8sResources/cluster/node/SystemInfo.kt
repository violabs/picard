package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class SystemInfo(
    val architecture: String,
    val bootID: String,
    val containerRuntimeVersion: String,
    val kernelVersion: String,
    val kubeProxyVersion: String,
    val kubeletVersion: String,
    val machineID: String,
    val operatingSystem: String,
    val osImage: String,
    val systemUUID: String
) {
    class Builder : DSLBuilder<SystemInfo> {
        var architecture: String? = null
        var bootID: String? = null
        var containerRuntimeVersion: String? = null
        var kernelVersion: String? = null
        var kubeProxyVersion: String? = null
        var kubeletVersion: String? = null
        var machineID: String? = null
        var operatingSystem: String? = null
        var osImage: String? = null
        var systemUUID: String? = null

        override fun build(): SystemInfo {
            return SystemInfo(
                architecture = vRequireNotNull(this::architecture),
                bootID = vRequireNotNull(this::bootID),
                containerRuntimeVersion = vRequireNotNull(this::containerRuntimeVersion),
                kernelVersion = vRequireNotNull(this::kernelVersion),
                kubeProxyVersion = vRequireNotNull(this::kubeProxyVersion),
                kubeletVersion = vRequireNotNull(this::kubeletVersion),
                machineID = vRequireNotNull(this::machineID),
                operatingSystem = vRequireNotNull(this::operatingSystem),
                osImage = vRequireNotNull(this::osImage),
                systemUUID = vRequireNotNull(this::systemUUID)
            )
        }
    }
}