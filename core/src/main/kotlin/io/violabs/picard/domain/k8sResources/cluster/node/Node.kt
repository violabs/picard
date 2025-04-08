package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.Taint
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.cluster.DaemonEndpoint
import io.violabs.picard.domain.k8sResources.storage.VolumeAttachment
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.NodeCondition
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerImage

class Node(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Node.Version> {
    interface Version : APIVersion

    data class Spec(
        val configSource: ConfigSource? = null,
        val externalID: String? = null,
        val podCIDR: String? = null,
        val providerID: String? = null,
        val taints: List<Taint>? = null,
        val value: Boolean? = null
    ) : BaseSpec

    data class Status(
        val addresses: List<Address>? = null,
        val allocatable: Map<String, Quantity>? = null,
        val capacity: Map<String, Quantity>? = null,
        val conditions: List<NodeCondition>? = null,
        val config: ConfigStatus? = null,
        val daemonEndpoints: DaemonEndpoints? = null,
        val features: Features? = null,
        val images: List<ContainerImage>? = null,
        val nodeInfo: SystemInfo? = null,
        val phase: String? = null,
        val runtimeHandlers: List<RuntimeHandler>? = null,
        val volumesAttached: List<VolumeAttachment>? = null,
        val volumesInUse: List<String>? = null
    ) : BaseStatus

    data class ConfigSource(
        val configMap: ConfigMap? = null
    ) {
        data class ConfigMap(
            val kubeletConfigKey: String,
            val name: String,
            val namespace: String,
            val resourceVersion: String? = null,
            val uid: String? = null
        )
    }

    data class Address(
        val address: String,
        val type: String
    )

    data class ConfigStatus(
        val active: ConfigSource? = null,
        val assigned: ConfigSource? = null,
        val error: String? = null,
        val lastKnownGood: ConfigSource? = null,
    )

    data class DaemonEndpoints(
        val kubeletEndpoint: DaemonEndpoint? = null
    )

    data class Features(
        val supplementalGroupsPolicy: Boolean? = null
    )

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
    )

    data class RuntimeHandler(
        val features: Features? = null,
        val name: String? = null
    ) {
        data class Features(
            val recursiveReadOnlyMounts: Boolean? = null,
            val userNamespaces: Boolean? = null
        )
    }

    data class AttachedVolume(
        val devicePath: String,
        val name: String
    )
}