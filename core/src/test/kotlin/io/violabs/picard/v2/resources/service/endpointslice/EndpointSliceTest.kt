package io.violabs.picard.v2.resources.service.endpointslice

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.domain.ObjectReference
import org.junit.jupiter.api.BeforeAll

class EndpointSliceTest : SuccessBuildSim<EndpointSlice, EndpointSliceV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointSliceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EndpointSlice, EndpointSliceV2DslBuilder> {
            scenario {
                id = "minimum"
                given(EndpointSliceV2DslBuilder()) {
                    addressType = "IPv4"
                    endpoints {
                        endpoint {
                            addresses("192.168.1.1")
                        }
                    }
                }
                expected = EndpointSlice(
                    addressType = "IPv4",
                    endpoints = listOf(
                        Endpoint(
                            addresses = listOf("192.168.1.1")
                        )
                    )
                )
            }

            scenario {
                id = "full"
                given(EndpointSliceV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    addressType = "IPv4"
                    endpoints {
                        endpoint {
                            addresses("192.168.1.1", "192.168.1.2")
                            conditions {
                                ready(true)
                                serving(true)
                                terminating(false)
                            }
                            hints {
                                forZones {
                                    forZone {
                                        name = PLACEHOLDER
                                    }
                                }
                                forNodes {
                                    forNode {
                                        name = PLACEHOLDER
                                    }
                                }
                            }
                            hostname = PLACEHOLDER
                            nodeName = PLACEHOLDER
                            targetRef = ObjectReference(
                                apiVersion = null,
                                kind = PLACEHOLDER,
                                name = PLACEHOLDER,
                                namespace = PLACEHOLDER,
                                resourceVersion = PLACEHOLDER,
                                uid = PLACEHOLDER
                            )
                            zone = PLACEHOLDER
                        }
                    }
                    ports {
                        endpointPort {
                            port = 80
                            protocol = "TCP"
                            name = PLACEHOLDER
                            appProtocol = PLACEHOLDER
                        }
                    }
                }
                expected = EndpointSlice(
                    metadata = OBJECT_META,
                    addressType = "IPv4",
                    endpoints = listOf(
                        Endpoint(
                            addresses = listOf("192.168.1.1", "192.168.1.2"),
                            conditions = EndpointConditions(
                                ready = true,
                                serving = true,
                                terminating = false
                            ),
                            hints = EndpointHints(
                                forZones = listOf(
                                    ForZone(name = PLACEHOLDER)
                                ),
                                forNodes = listOf(
                                    ForNode(name = PLACEHOLDER)
                                )
                            ),
                            hostname = PLACEHOLDER,
                            nodeName = PLACEHOLDER,
                            targetRef = ObjectReference(
                                apiVersion = null,
                                kind = PLACEHOLDER,
                                name = PLACEHOLDER,
                                namespace = PLACEHOLDER,
                                resourceVersion = PLACEHOLDER,
                                uid = PLACEHOLDER
                            ),
                            zone = PLACEHOLDER
                        )
                    ),
                    ports = listOf(
                        EndpointPort(
                            port = 80,
                            protocol = "TCP",
                            name = PLACEHOLDER,
                            appProtocol = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}