package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.service.endpointSlice.EndpointSlice
import io.violabs.picard.domain.k8sResources.service.endpointSlice.EndpointSliceList
import io.violabs.picard.domain.k8sResources.service.endpoints.Endpoints
import io.violabs.picard.domain.k8sResources.service.endpoints.EndpointsList
import io.violabs.picard.domain.k8sResources.service.ingress.Ingress
import io.violabs.picard.domain.k8sResources.service.ingress.IngressList
import io.violabs.picard.domain.k8sResources.service.ingressClass.IngressClass
import io.violabs.picard.domain.k8sResources.service.ingressClass.IngressClassList
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceResourceSectionTest : FullBuildSim<ServiceResourceSection, ServiceResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceResourceSectionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceResourceSection, ServiceResourceSection.Builder> {
            scenario {
                id = "full"
                given(ServiceResourceSection.Builder()) {
                    endpoints { }
                    endpointsList {
                        items {
                            endpointsItem { }
                        }
                    }
                    endpointSlice { }
                    endpointSliceList {
                        items {
                            endpointSliceItem { }
                        }
                    }
                    ingress { }
                    ingressList {
                        items {
                            ingressItem { }
                        }
                    }
                    ingressClass { }
                    ingressClassList {
                        items {
                            ingressClassItem { }
                        }
                    }
                }
                expected = ServiceResourceSection(
                    resources = listOf(
                        EndpointSlice(),
                        EndpointSliceList(items = listOf(EndpointSlice())),
                        Endpoints(),
                        EndpointsList(items = listOf(Endpoints())),
                        Ingress(),
                        IngressClass(),
                        IngressClassList(items = listOf(IngressClass())),
                        IngressList(items = listOf(Ingress()))
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServiceResourceSection, ServiceResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(ServiceResourceSection.Builder())
            }
        }
    }
}