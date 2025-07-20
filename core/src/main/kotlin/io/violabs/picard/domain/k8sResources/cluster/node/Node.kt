package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.condition.NodeCondition
import io.violabs.picard.domain.condition.NodeConditionGroup
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.manifest.ClusterResource

data class Node(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : ClusterResource<Node.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val configSource: NodeConfigSource? = null,
        val externalID: String? = null,
        val podCIDR: String? = null,
        val providerID: String? = null,
        val taints: List<Taint>? = null,
        val value: Boolean? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var configSource: NodeConfigSource? = null
            var externalID: String? = null
            var podCIDR: String? = null
            var providerID: String? = null
            private var taints: List<Taint>? = null
            private var value: Boolean? = null

            fun configSource(scope: NodeConfigSource.Builder.() -> Unit) {
                configSource = NodeConfigSource.Builder().apply(scope).build()
            }

            fun taints(scope: Taint.Group.() -> Unit) {
                taints = Taint.Group().apply(scope).taints()
            }

            fun value(value: Boolean = true) {
                this.value = value
            }

            override fun build(): Spec {
                return Spec(
                    configSource = configSource,
                    externalID = externalID,
                    podCIDR = podCIDR,
                    providerID = providerID,
                    taints = taints,
                    value = value
                )
            }
        }
    }

    data class Status(
        val addresses: List<NodeAddress>? = null,
        val allocatable: Map<String, Quantity>? = null,
        val capacity: Map<String, Quantity>? = null,
        val conditions: List<NodeCondition>? = null,
        val config: NodeConfigStatus? = null,
        val daemonEndpoints: DaemonEndpoints? = null,
        val features: NodeFeatures? = null,
        val images: List<ContainerImage>? = null,
        val nodeInfo: SystemInfo? = null,
        val phase: String? = null,
        val runtimeHandlers: List<RuntimeHandler>? = null,
        val volumesAttached: List<AttachedVolume>? = null,
        val volumesInUse: List<String>? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var addresses: List<NodeAddress>? = null
            private var allocatable: Map<String, Quantity>? = null
            private var capacity: Map<String, Quantity>? = null
            private var conditions: List<NodeCondition>? = null
            private var config: NodeConfigStatus? = null
            private var daemonEndpoints: DaemonEndpoints? = null
            private var features: NodeFeatures? = null
            private var images: List<ContainerImage>? = null
            private var nodeInfo: SystemInfo? = null
            var phase: String? = null
            private var runtimeHandlers: List<RuntimeHandler>? = null
            private var volumesAttached: List<AttachedVolume>? = null
            private var volumesInUse: List<String>? = null

            fun addresses(scope: NodeAddress.Group.() -> Unit) {
                addresses = NodeAddress.Group().apply(scope).addresses()
            }

            fun allocatable(vararg allocatable: Pair<String, Quantity>) {
                this.allocatable = allocatable.toMap()
            }

            fun capacity(vararg capacity: Pair<String, Quantity>) {
                this.capacity = capacity.toMap()
            }

            fun conditions(scope: NodeConditionGroup.() -> Unit) {
                conditions = NodeCondition.group(scope)
            }

            fun config(scope: NodeConfigStatus.Builder.() -> Unit) {
                config = NodeConfigStatus.Builder().apply(scope).build()
            }

            fun daemonEndpoints(scope: DaemonEndpoints.Builder.() -> Unit) {
                daemonEndpoints = DaemonEndpoints.Builder().apply(scope).build()
            }

            fun features(scope: NodeFeatures.Builder.() -> Unit) {
                features = NodeFeatures.Builder().apply(scope).build()
            }

            fun images(scope: ContainerImage.Group.() -> Unit) {
                images = ContainerImage.Group().apply(scope).images()
            }

            fun nodeInfo(scope: SystemInfo.Builder.() -> Unit) {
                nodeInfo = SystemInfo.Builder().apply(scope).build()
            }

            fun runtimeHandlers(scope: RuntimeHandler.Group.() -> Unit) {
                runtimeHandlers = RuntimeHandler.Group().apply(scope).handlers()
            }

            fun volumesAttached(scope: AttachedVolume.Group.() -> Unit) {
                volumesAttached = AttachedVolume.Group().apply(scope).volumes()
            }

            fun volumesInUse(vararg volumes: String) {
                volumesInUse = volumes.toList()
            }

            override fun build(): Status {
                return Status(
                    addresses = addresses,
                    allocatable = allocatable,
                    capacity = capacity,
                    conditions = conditions,
                    config = config,
                    daemonEndpoints = daemonEndpoints,
                    features = features,
                    images = images,
                    nodeInfo = nodeInfo,
                    phase = phase,
                    runtimeHandlers = runtimeHandlers,
                    volumesAttached = volumesAttached,
                    volumesInUse = volumesInUse
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        Node,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): Node {
            return Node(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Node, Builder>(Builder()) {
        fun nodeItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}