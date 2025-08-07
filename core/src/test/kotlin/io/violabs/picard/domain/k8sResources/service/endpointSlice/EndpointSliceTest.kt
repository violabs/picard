package io.violabs.picard.domain.k8sResources.service.endpointSlice


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.service.endpoint.Endpoint
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointCondition
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointHints
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointPort
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointSliceTest : SuccessBuildSim<EndpointSlice, EndpointSliceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointSliceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val ENDPOINT = Endpoint(
            addresses = PLACEHOLDER_LIST,
            conditions = listOf(
                EndpointCondition(
                    ready = true,
                    serving = true,
                    terminating = true
                )
            ),
            hints = EndpointHints(
                forZones = listOf(EndpointHints.ForZone(PLACEHOLDER))
            ),
            hostname = PLACEHOLDER,
            nodeName = PLACEHOLDER,
            targetRef = ObjectReference(
                apiVersion = KAPIVersion.V1,
                kind = PLACEHOLDER,
                name = PLACEHOLDER,
                namespace = PLACEHOLDER,
                uid = PLACEHOLDER,
                fieldPath = PLACEHOLDER,
                resourceVersion = PLACEHOLDER
            ),
            zone = PLACEHOLDER
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EndpointSlice, EndpointSliceDslBuilder> {
            scenario {
                id = "minimum"
                given(EndpointSliceDslBuilder())
                expected = EndpointSlice()
            }

            scenario {
                id = "full"
                given(EndpointSliceDslBuilder()) {
                    sharedMetadata()
                    endpoints {
                        endpoint {
                            addresses(PLACEHOLDER)
                            conditions {
                                addEndpointCondition {
                                    ready()
                                    serving()
                                    terminating()
                                }
                            }
                            hints {
                                forZones(PLACEHOLDER)
                            }
                            hostname = PLACEHOLDER
                            nodeName = PLACEHOLDER
                            targetRef {
                                apiVersion = KAPIVersion.V1
                                kind = PLACEHOLDER
                                name = PLACEHOLDER
                                namespace = PLACEHOLDER
                                uid = PLACEHOLDER
                                fieldPath = PLACEHOLDER
                                resourceVersion = PLACEHOLDER
                            }
                            zone = PLACEHOLDER
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
                expected = EndpointSlice(
                    metadata = METADATA,
                    endpoints = listOf(ENDPOINT),
                    ports = listOf(
                        EndpointPort(
                            port = PORT_NUMBER,
                            protocol = Protocol.TCP,
                            name = PLACEHOLDER,
                            appProtocol = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}