package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.cluster.component.status.ComponentStatusDslBuilder
import io.violabs.picard.v2.resources.cluster.component.status.ComponentStatusDslBuilderScope
import io.violabs.picard.v2.resources.cluster.component.status.ComponentStatusListDslBuilder
import io.violabs.picard.v2.resources.cluster.component.status.ComponentStatusListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.event.EventDslBuilder
import io.violabs.picard.v2.resources.cluster.event.EventDslBuilderScope
import io.violabs.picard.v2.resources.cluster.event.EventListDslBuilder
import io.violabs.picard.v2.resources.cluster.event.EventListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.ipaddress.IpAddressDslBuilder
import io.violabs.picard.v2.resources.cluster.ipaddress.IpAddressDslBuilderScope
import io.violabs.picard.v2.resources.cluster.ipaddress.IpAddressListDslBuilder
import io.violabs.picard.v2.resources.cluster.ipaddress.IpAddressListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.lease.LeaseDslBuilder
import io.violabs.picard.v2.resources.cluster.lease.LeaseDslBuilderScope
import io.violabs.picard.v2.resources.cluster.lease.LeaseListDslBuilder
import io.violabs.picard.v2.resources.cluster.lease.LeaseListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.lease.candidate.LeaseCandidateDslBuilder
import io.violabs.picard.v2.resources.cluster.lease.candidate.LeaseCandidateDslBuilderScope
import io.violabs.picard.v2.resources.cluster.lease.candidate.LeaseCandidateListDslBuilder
import io.violabs.picard.v2.resources.cluster.lease.candidate.LeaseCandidateListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.namespace.NamespaceDslBuilder
import io.violabs.picard.v2.resources.cluster.namespace.NamespaceDslBuilderScope
import io.violabs.picard.v2.resources.cluster.namespace.NamespaceListDslBuilder
import io.violabs.picard.v2.resources.cluster.namespace.NamespaceListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.node.NodeDslBuilder
import io.violabs.picard.v2.resources.cluster.node.NodeDslBuilderScope
import io.violabs.picard.v2.resources.cluster.node.NodeListDslBuilder
import io.violabs.picard.v2.resources.cluster.node.NodeListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.runtimeclass.RuntimeClassDslBuilder
import io.violabs.picard.v2.resources.cluster.runtimeclass.RuntimeClassDslBuilderScope
import io.violabs.picard.v2.resources.cluster.runtimeclass.RuntimeClassListDslBuilder
import io.violabs.picard.v2.resources.cluster.runtimeclass.RuntimeClassListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.service.api.ApiServiceDslBuilder
import io.violabs.picard.v2.resources.cluster.service.api.ApiServiceDslBuilderScope
import io.violabs.picard.v2.resources.cluster.service.api.ApiServiceListDslBuilder
import io.violabs.picard.v2.resources.cluster.service.api.ApiServiceListDslBuilderScope
import io.violabs.picard.v2.resources.cluster.service.cidr.ServiceCidrDslBuilder
import io.violabs.picard.v2.resources.cluster.service.cidr.ServiceCidrDslBuilderScope
import io.violabs.picard.v2.resources.cluster.service.cidr.ServiceCidrListDslBuilder
import io.violabs.picard.v2.resources.cluster.service.cidr.ServiceCidrListDslBuilderScope

interface ClusterResource<T : APIVersion, META> : K8sResource<T, META>
interface ClusterListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ClusterResourceSection(
    override val resources: List<K8sAPIResource<*>>
): ManifestResource {
    class Builder(
        private val resources: MutableList<ClusterResource<*, *>> = mutableListOf(),
        private val lists: MutableList<ClusterListResource<*, *>> = mutableListOf()
    ) : DslBuilder<ClusterResourceSection> {
        fun apiService(block: ApiServiceDslBuilderScope) {
            val apiService = ApiServiceDslBuilder().apply(block).build()
            resources.add(apiService)
        }

        fun apiServiceList(block: ApiServiceListDslBuilderScope) {
            val list = ApiServiceListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun componentStatus(block: ComponentStatusDslBuilderScope) {
            val componentStatus = ComponentStatusDslBuilder().apply(block).build()
            resources.add(componentStatus)
        }

        fun componentStatusList(block: ComponentStatusListDslBuilderScope) {
            val list = ComponentStatusListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun event(block: EventDslBuilderScope) {
            val event = EventDslBuilder().apply(block).build()
            resources.add(event)
        }

        fun eventList(block: EventListDslBuilderScope) {
            val list = EventListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun ipAddress(block: IpAddressDslBuilderScope) {
            val ipAddress = IpAddressDslBuilder().apply(block).build()
            resources.add(ipAddress)
        }

        fun ipAddressList(block: IpAddressListDslBuilderScope) {
            val list = IpAddressListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun lease(block: LeaseDslBuilderScope) {
            val lease = LeaseDslBuilder().apply(block).build()
            resources.add(lease)
        }

        fun leaseList(block: LeaseListDslBuilderScope) {
            val list = LeaseListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun leaseCandidate(block: LeaseCandidateDslBuilderScope) {
            val leaseCandidate = LeaseCandidateDslBuilder().apply(block).build()
            resources.add(leaseCandidate)
        }

        fun leaseCandidateList(block: LeaseCandidateListDslBuilderScope) {
            val list = LeaseCandidateListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun namespace(block: NamespaceDslBuilderScope) {
            val namespace = NamespaceDslBuilder().apply(block).build()
            resources.add(namespace)
        }

        fun namespaceList(block: NamespaceListDslBuilderScope) {
            val list = NamespaceListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun node(block: NodeDslBuilderScope) {
            val node = NodeDslBuilder().apply(block).build()
            resources.add(node)
        }

        fun nodeList(block: NodeListDslBuilderScope) {
            val list = NodeListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun runtimeClass(block: RuntimeClassDslBuilderScope) {
            val runtimeClass = RuntimeClassDslBuilder().apply(block).build()
            resources.add(runtimeClass)
        }

        fun runtimeClassList(block: RuntimeClassListDslBuilderScope) {
            val list = RuntimeClassListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun serviceCidr(block: ServiceCidrDslBuilderScope) {
            val serviceCidr = ServiceCidrDslBuilder().apply(block).build()
            resources.add(serviceCidr)
        }

        fun serviceCidrList(block: ServiceCidrListDslBuilderScope) {
            val list = ServiceCidrListDslBuilder().apply(block).build()
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