package io.violabs.picard.v2.resources.service.endpoints

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.domain.ObjectReference
import org.junit.jupiter.api.BeforeAll

class EndpointsTest : SuccessBuildSim<EndpointsV2, EndpointsV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointsTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EndpointsV2, EndpointsV2DslBuilder> {
            scenario {
                id = "minimum"
                given(EndpointsV2DslBuilder())
                expected = EndpointsV2()
            }

            scenario {
                id = "full"
                given(EndpointsV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    subsets {
                        endpointSubset {
                            addresses {
                                endpointAddress {
                                    ip = PLACEHOLDER
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
                                }
                            }
                            
                            notReadyAddresses {
                                endpointAddress {
                                    ip = PLACEHOLDER
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
                    }
                }
                expected = EndpointsV2(
                    metadata = OBJECT_META,
                    subsets = listOf(
                        EndpointSubset(
                            addresses = listOf(
                                EndpointAddress(
                                    ip = PLACEHOLDER,
                                    hostname = PLACEHOLDER,
                                    nodeName = PLACEHOLDER,
                                    targetRef = ObjectReference(
                                        apiVersion = null,
                                        kind = PLACEHOLDER,
                                        name = PLACEHOLDER,
                                        namespace = PLACEHOLDER,
                                        resourceVersion = PLACEHOLDER,
                                        uid = PLACEHOLDER
                                    )
                                )
                            ),
                            notReadyAddresses = listOf(
                                EndpointAddress(
                                    ip = PLACEHOLDER,
                                    hostname = PLACEHOLDER,
                                    nodeName = PLACEHOLDER,
                                    targetRef = ObjectReference(
                                        apiVersion = null,
                                        kind = PLACEHOLDER,
                                        name = PLACEHOLDER,
                                        namespace = PLACEHOLDER,
                                        resourceVersion = PLACEHOLDER,
                                        uid = PLACEHOLDER
                                    )
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
                    )
                )
            }
        }
    }
}