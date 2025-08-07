package io.violabs.picard.domain.k8sResources.policy.networkPolicy


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.policy.IPBlock
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NetworkPolicyTest : SuccessBuildSim<NetworkPolicy, NetworkPolicyDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicyTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val NETWORK_POLICY_PEER = NetworkPolicyPeer(
            ipBlock = IPBlock(
                cidr = PLACEHOLDER,
                except = PLACEHOLDER_LIST
            ),
            namespaceSelector = LABEL_SELECTOR,
            podSelector = LABEL_SELECTOR
        )

        private fun NetworkPolicyPeerDslBuilder.sharedNetworkPolicyPeer() {
            ipBlock {
                cidr = PLACEHOLDER
                except(PLACEHOLDER)
            }
            namespaceSelector {
                sharedSelector()
            }
            podSelector {
                sharedSelector()
            }
        }

        private val NETWORK_POLICY_PORT = NetworkPolicyPort(
            port = IntOrString(num = PORT_NUMBER),
            endPort = PORT_NUMBER,
            protocol = Protocol.TCP
        )

        private fun NetworkPolicyPortDslBuilder.sharedNetworkPolicyPort() {
            port(PORT_NUMBER)
            endPort = PORT_NUMBER
            protocol = Protocol.TCP
        }

        private val NETWORK_POLICY_INGRESS_RULE = NetworkPolicyIngressRule(
            from = listOf(NETWORK_POLICY_PEER),
            ports = listOf(NETWORK_POLICY_PORT),
            egress = listOf(NetworkPolicyEgressRule(
                to = listOf(NETWORK_POLICY_PEER),
                ports = listOf(NETWORK_POLICY_PORT)
            ))
        )

        private val SUCCESS_POSSIBILITIES = possibilities<NetworkPolicy, NetworkPolicyDslBuilder> {
            scenario {
                id = "minimum"
                given(NetworkPolicyDslBuilder())
                expected = NetworkPolicy()
            }

            scenario {
                id = "full"
                given(NetworkPolicyDslBuilder()) {
                    sharedMetadata()
                    spec {
                        podSelector {
                            sharedSelector()
                        }
                        policyTypes(PLACEHOLDER_LIST)
                        ingress {
                            addNetworkPolicyIngressRule {
                                from {
                                    addNetworkPolicyPeer {
                                        sharedNetworkPolicyPeer()
                                    }
                                }
                                ports {
                                    port {
                                        sharedNetworkPolicyPort()
                                    }
                                }
                                egress {
                                    addNetworkPolicyEgressRule {
                                        to {
                                            addNetworkPolicyPeer {
                                                sharedNetworkPolicyPeer()
                                            }
                                        }
                                        ports {
                                            port {
                                                sharedNetworkPolicyPort()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                expected = NetworkPolicy(
                    metadata = METADATA,
                    spec = NetworkPolicySpec(
                        podSelector = LABEL_SELECTOR,
                        policyTypes = PLACEHOLDER_LIST,
                        ingress = listOf(NETWORK_POLICY_INGRESS_RULE)
                    )
                )
            }
        }
    }
}