package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.cluster.apiService.APIService
import io.violabs.picard.domain.k8sResources.cluster.apiService.APIServiceList
import io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatus
import io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatusList
import io.violabs.picard.domain.k8sResources.cluster.event.Event
import io.violabs.picard.domain.k8sResources.cluster.event.EventList
import io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddress
import io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddressList
import io.violabs.picard.domain.k8sResources.cluster.lease.Lease
import io.violabs.picard.domain.k8sResources.cluster.lease.LeaseList
import io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidate
import io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidateList
import io.violabs.picard.domain.k8sResources.cluster.namespace.Namespace
import io.violabs.picard.domain.k8sResources.cluster.namespace.NamespaceList
import io.violabs.picard.domain.k8sResources.cluster.node.Node
import io.violabs.picard.domain.k8sResources.cluster.node.NodeList
import io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClass
import io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClassList
import io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDR
import io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDRList

interface ClusterResource<T : APIVersion> : K8sResource<T>
interface ClusterListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ClusterResourceSection(
    val resources: List<K8sAPIResource<*>>
): ManifestResource {
    class Builder(
        private val resources: MutableList<ClusterResource<*>> = mutableListOf(),
        private val lists: MutableList<ClusterListResource<*, *>> = mutableListOf()
    ) : DSLBuilder<ClusterResourceSection> {
        fun apiService(block: APIService.Builder.() -> Unit) {
            val apiService = APIService.Builder().apply(block).build()
            resources.add(apiService)
        }

        fun apiServiceList(block: APIServiceList.Builder.() -> Unit) {
            val list = APIServiceList.Builder().apply(block).build()
            lists.add(list)
        }

        fun componentStatus(block: ComponentStatus.Builder.() -> Unit) {
            val componentStatus = ComponentStatus.Builder().apply(block).build()
            resources.add(componentStatus)
        }

        fun componentStatusList(block: ComponentStatusList.Builder.() -> Unit) {
            val list = ComponentStatusList.Builder().apply(block).build()
            lists.add(list)
        }

        fun event(block: Event.Builder.() -> Unit) {
            val event = Event.Builder().apply(block).build()
            resources.add(event)
        }

        fun eventList(block: EventList.Builder.() -> Unit) {
            val list = EventList.Builder().apply(block).build()
            lists.add(list)
        }

        fun ipAddress(block: IPAddress.Builder.() -> Unit) {
            val ipAddress = IPAddress.Builder().apply(block).build()
            resources.add(ipAddress)
        }

        fun ipAddressList(block: IPAddressList.Builder.() -> Unit) {
            val list = IPAddressList.Builder().apply(block).build()
            lists.add(list)
        }

        fun lease(block: Lease.Builder.() -> Unit) {
            val lease = Lease.Builder().apply(block).build()
            resources.add(lease)
        }

        fun leaseList(block: LeaseList.Builder.() -> Unit) {
            val list = LeaseList.Builder().apply(block).build()
            lists.add(list)
        }

        fun leaseCandidate(block: LeaseCandidate.Builder.() -> Unit) {
            val leaseCandidate = LeaseCandidate.Builder().apply(block).build()
            resources.add(leaseCandidate)
        }

        fun leaseCandidateList(block: LeaseCandidateList.Builder.() -> Unit) {
            val list = LeaseCandidateList.Builder().apply(block).build()
            lists.add(list)
        }

        fun namespace(block: Namespace.Builder.() -> Unit) {
            val namespace = Namespace.Builder().apply(block).build()
            resources.add(namespace)
        }

        fun namespaceList(block: NamespaceList.Builder.() -> Unit) {
            val list = NamespaceList.Builder().apply(block).build()
            lists.add(list)
        }

        fun node(block: Node.Builder.() -> Unit) {
            val node = Node.Builder().apply(block).build()
            resources.add(node)
        }

        fun nodeList(block: NodeList.Builder.() -> Unit) {
            val list = NodeList.Builder().apply(block).build()
            lists.add(list)
        }

        fun runtimeClass(block: RuntimeClass.Builder.() -> Unit) {
            val runtimeClass = RuntimeClass.Builder().apply(block).build()
            resources.add(runtimeClass)
        }

        fun runtimeClassList(block: RuntimeClassList.Builder.() -> Unit) {
            val list = RuntimeClassList.Builder().apply(block).build()
            lists.add(list)
        }

        fun serviceCIDR(block: ServiceCIDR.Builder.() -> Unit) {
            val serviceCIDR = ServiceCIDR.Builder().apply(block).build()
            resources.add(serviceCIDR)
        }

        fun serviceCIDRList(block: ServiceCIDRList.Builder.() -> Unit) {
            val list = ServiceCIDRList.Builder().apply(block).build()
            lists.add(list)
        }

        override fun build(): ClusterResourceSection {
            return ClusterResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}