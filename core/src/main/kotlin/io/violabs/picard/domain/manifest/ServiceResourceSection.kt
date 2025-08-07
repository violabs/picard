package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource

interface ServiceResource<T : APIVersion, META> : K8sResource<T, META>
interface ServiceListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ServiceResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<ServiceResource<*, *>> = mutableListOf(),
        private val lists: MutableList<ServiceListResource<*, *>> = mutableListOf()
    ) : DslBuilder<ServiceResourceSection> {
//        fun endpoints(block: Endpoints.Builder.() -> Unit) {
//            resources += Endpoints.Builder().apply(block).build()
//        }
//
//        fun endpointsList(block: EndpointsList.Builder.() -> Unit) {
//            lists += EndpointsList.Builder().apply(block).build()
//        }
//
//        fun endpointSlice(block: EndpointSlice.Builder.() -> Unit) {
//            resources += EndpointSlice.Builder().apply(block).build()
//        }
//
//        fun endpointSliceList(block: EndpointSliceList.Builder.() -> Unit) {
//            lists += EndpointSliceList.Builder().apply(block).build()
//        }
//
//        fun ingress(block: Ingress.Builder.() -> Unit) {
//            resources += Ingress.Builder().apply(block).build()
//        }
//
//        fun ingressList(block: IngressList.Builder.() -> Unit) {
//            lists += IngressList.Builder().apply(block).build()
//        }
//
//        fun ingressClass(block: IngressClass.Builder.() -> Unit) {
//            resources += IngressClass.Builder().apply(block).build()
//        }
//
//        fun ingressClassList(block: IngressClassList.Builder.() -> Unit) {
//            lists += IngressClassList.Builder().apply(block).build()
//        }
//
//        fun service(block: Service.Builder.() -> Unit) {
//            resources += Service.Builder().apply(block).build()
//        }
//
//        fun serviceList(block: ServiceList.Builder.() -> Unit) {
//            lists += ServiceList.Builder().apply(block).build()
//        }

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