package io.violabs.picard.v2.resources.policy.network


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.LabelSelectorRequirement
import org.junit.jupiter.api.BeforeAll

class NetworkPolicyTest : SuccessBuildSim<NetworkPolicy, NetworkPolicyV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicyTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val NETWORK_POLICY_PEER = NetworkPolicyPeer(
            ipBlock = IpBlock(
                cidr = PLACEHOLDER,
                except = PLACEHOLDER_LIST
            ),
            namespaceSelector = Common.LABEL_SELECTOR,
            podSelector = Common.LABEL_SELECTOR
        )

        private val NETWORK_POLICY_PORT = NetworkPolicyPort(
            port = IntOrString(num = PORT_NUMBER),
            endPort = PORT_NUMBER,
            protocol = PLACEHOLDER
        )

        private val NETWORK_POLICY_INGRESS_RULE = NetworkPolicyIngressRule(
            from = listOf(NETWORK_POLICY_PEER),
            ports = listOf(NETWORK_POLICY_PORT)
        )

        private val NETWORK_POLICY_EGRESS_RULE = NetworkPolicyEgressRule(
            to = listOf(NETWORK_POLICY_PEER),
            ports = listOf(NETWORK_POLICY_PORT)
        )

        private val SUCCESS_POSSIBILITIES = possibilities<NetworkPolicy, NetworkPolicyV2DslBuilder> {
            scenario {
                id = "minimum"
                given(NetworkPolicyV2DslBuilder())
                expected = NetworkPolicy()
            }

            scenario {
                id = "full"
                given(NetworkPolicyV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        podSelector {
                            matchExpressions {
                                labelSelectorRequirement {
                                    key = PLACEHOLDER
                                    operator = LabelSelectorRequirement.Operator.In
                                    values(PLACEHOLDER)
                                }
                            }

                            matchLabels(PLACEHOLDER to PLACEHOLDER)
                        }
                        policyTypes(PLACEHOLDER)
                        ingress {
                            networkPolicyIngressRule {
                                from {
                                    networkPolicyPeer {
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
                                }
                                ports {
                                    networkPolicyPort {
                                        port = IntOrString(8080)
                                        endPort = PORT_NUMBER
                                        protocol = PLACEHOLDER
                                    }
                                }
                            }
                        }
                        egress {
                            networkPolicyEgressRule {
                                to {
                                    networkPolicyPeer {
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
                                }
                                ports {
                                    networkPolicyPort {
                                        port = IntOrString(8080)
                                        endPort = PORT_NUMBER
                                        protocol = PLACEHOLDER
                                    }
                                }
                            }
                        }
                    }
                }
                expected = NetworkPolicy(
                    metadata = Common.OBJECT_META,
                    spec = NetworkPolicySpec(
                        podSelector = Common.LABEL_SELECTOR,
                        policyTypes = PLACEHOLDER_LIST,
                        ingress = listOf(NETWORK_POLICY_INGRESS_RULE),
                        egress = listOf(NETWORK_POLICY_EGRESS_RULE)
                    )
                )
            }
        }
    }
}