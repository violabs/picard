package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.service.ServiceDslBuilder
import io.violabs.picard.v2.resources.service.ServiceDslBuilderScope
import io.violabs.picard.v2.resources.service.ServiceListDslBuilder
import io.violabs.picard.v2.resources.service.ServiceListDslBuilderScope
import io.violabs.picard.v2.resources.service.endpoints.EndpointsDslBuilder
import io.violabs.picard.v2.resources.service.endpoints.EndpointsDslBuilderScope
import io.violabs.picard.v2.resources.service.endpoints.EndpointsListDslBuilder
import io.violabs.picard.v2.resources.service.endpoints.EndpointsListDslBuilderScope
import io.violabs.picard.v2.resources.service.slice.EndpointSliceDslBuilder
import io.violabs.picard.v2.resources.service.slice.EndpointSliceDslBuilderScope
import io.violabs.picard.v2.resources.service.slice.EndpointSliceListDslBuilder
import io.violabs.picard.v2.resources.service.slice.EndpointSliceListDslBuilderScope
import io.violabs.picard.v2.resources.service.ingress.IngressDslBuilder
import io.violabs.picard.v2.resources.service.ingress.IngressDslBuilderScope
import io.violabs.picard.v2.resources.service.ingress.IngressListDslBuilder
import io.violabs.picard.v2.resources.service.ingress.IngressListDslBuilderScope
import io.violabs.picard.v2.resources.service.ingressclass.IngressClassDslBuilder
import io.violabs.picard.v2.resources.service.ingressclass.IngressClassDslBuilderScope
import io.violabs.picard.v2.resources.service.ingressclass.IngressClassListDslBuilder
import io.violabs.picard.v2.resources.service.ingressclass.IngressClassListDslBuilderScope

interface ServiceResource<T : APIVersion, META> : K8sResource<T, META>
interface ServiceListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ServiceResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<ServiceResource<*, *>> = mutableListOf(),
        private val lists: MutableList<ServiceListResource<*, *>> = mutableListOf()
    ) : DslBuilder<ServiceResourceSection> {

        fun endpoints(block: EndpointsDslBuilderScope) {
            val endpoints = EndpointsDslBuilder().apply(block).build()
            resources.add(endpoints)
        }

        fun endpointsList(block: EndpointsListDslBuilderScope) {
            val list = EndpointsListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun endpointSlice(block: EndpointSliceDslBuilderScope) {
            val endpointSlice = EndpointSliceDslBuilder().apply(block).build()
            resources.add(endpointSlice)
        }

        fun endpointSliceList(block: EndpointSliceListDslBuilderScope) {
            val list = EndpointSliceListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun ingress(block: IngressDslBuilderScope) {
            val ingress = IngressDslBuilder().apply(block).build()
            resources.add(ingress)
        }

        fun ingressList(block: IngressListDslBuilderScope) {
            val list = IngressListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun ingressClass(block: IngressClassDslBuilderScope) {
            val ingressClass = IngressClassDslBuilder().apply(block).build()
            resources.add(ingressClass)
        }

        fun ingressClassList(block: IngressClassListDslBuilderScope) {
            val list = IngressClassListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun service(block: ServiceDslBuilderScope) {
            val service = ServiceDslBuilder().apply(block).build()
            resources.add(service)
        }

        fun serviceList(block: ServiceListDslBuilderScope) {
            val list = ServiceListDslBuilder().apply(block).build()
            lists.add(list)
        }

        override fun build(): ServiceResourceSection {
            return ServiceResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}