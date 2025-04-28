package io.violabs.picard.domain.k8sResources.service.endpoints


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointAddress
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointPort
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointSubset
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointsTest : SuccessBuildSim<Endpoints, Endpoints.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointsTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val OBJECT_REF = ObjectReference(
            apiVersion = KAPIVersion.V1,
            fieldPath = PLACEHOLDER,
            kind = PLACEHOLDER,
            name = PLACEHOLDER,
            namespace = PLACEHOLDER,
            resourceVersion = PLACEHOLDER,
            uid = PLACEHOLDER
        )

        private val ADDRESS = EndpointAddress(
            ip = PLACEHOLDER,
            hostname = PLACEHOLDER,
            nodeName = PLACEHOLDER,
            targetRef = OBJECT_REF
        )

        private val PORT = EndpointPort(
            port = PORT_NUMBER,
            protocol = Protocol.TCP,
            name = PLACEHOLDER,
            appProtocol = PLACEHOLDER
        )

        private val ENDPOINT_SUBSET = EndpointSubset(
            addresses = listOf(ADDRESS),
            notReadyAddresses = listOf(ADDRESS),
            ports = listOf(PORT)
        )

        private fun EndpointAddress.Builder.sharedAddress() {
            ip = PLACEHOLDER
            hostname = PLACEHOLDER
            nodeName = PLACEHOLDER
            targetRef  {
                apiVersion = KAPIVersion.V1
                fieldPath = PLACEHOLDER
                kind = PLACEHOLDER
                name = PLACEHOLDER
                namespace = PLACEHOLDER
                resourceVersion = PLACEHOLDER
                uid = PLACEHOLDER
            }
        }

        private val SUCCESS_POSSIBILITIES = possibilities<Endpoints, Endpoints.Builder> {
            scenario {
                id = "minimum"
                given(Endpoints.Builder())
                expected = Endpoints()
            }

            scenario {
                id = "full"
                given(Endpoints.Builder()) {
                    sharedMetadata()
                    subsets {
                        subset {
                            addresses {
                                address {
                                    sharedAddress()
                                }
                            }

                            notReadyAddresses {
                                address {
                                    sharedAddress()
                                }
                            }

                            ports {
                                port {
                                    port = PORT_NUMBER
                                    protocol = Protocol.TCP
                                    name = PLACEHOLDER
                                    appProtocol = PLACEHOLDER
                                }
                            }
                        }
                    }
                }
                expected = Endpoints(
                    metadata = METADATA,
                    subsets = listOf(ENDPOINT_SUBSET)
                )
            }
        }
    }
}