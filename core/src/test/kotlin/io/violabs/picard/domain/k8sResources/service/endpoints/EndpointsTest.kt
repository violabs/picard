package io.violabs.picard.domain.k8sResources.service.endpoints


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointAddress
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointPort
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointSubset
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointsTest : SuccessBuildSim<Endpoints, EndpointsDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointsTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val ADDRESS = EndpointAddress(
            ip = PLACEHOLDER,
            hostname = PLACEHOLDER,
            nodeName = PLACEHOLDER,
            targetRef = OBJECT_REFERENCE
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

        private fun EndpointAddressDslBuilder.sharedAddress() {
            ip = PLACEHOLDER
            hostname = PLACEHOLDER
            nodeName = PLACEHOLDER
            targetRef {
                sharedObjectReference()
            }
        }

        private val SUCCESS_POSSIBILITIES = possibilities<Endpoints, EndpointsDslBuilder> {
            scenario {
                id = "minimum"
                given(EndpointsDslBuilder())
                expected = Endpoints()
            }

            scenario {
                id = "full"
                given(EndpointsDslBuilder()) {
                    sharedMetadata()
                    subsets {
                        addEndpointSubset {
                            addresses {
                                addEndpointAddress {
                                    sharedAddress()
                                }
                            }

                            notReadyAddresses {
                                addEndpointAddress {
                                    sharedAddress()
                                }
                            }

                            ports {
                                addEndpointPort {
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