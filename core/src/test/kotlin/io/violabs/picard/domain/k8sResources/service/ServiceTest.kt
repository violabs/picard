package io.violabs.picard.domain.k8sResources.service


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceTest : SuccessBuildSim<Service, Service.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SERVICE_PORT = ServicePort(
            ports = PORT_NUMBER,
            targetPort = IntOrString(PORT_NUMBER),
            protocol = Protocol.TCP,
            name = PLACEHOLDER,
            nodePort = PORT_NUMBER,
            appProtocol = PLACEHOLDER
        )

        private val SESSION_AFFINITY_CONFIG = SessionAffinityConfig(
            clientIP = ClientIPConfig(1)
        )

        private val LOAD_BALANCER_STATUS = LoadBalancerStatus(
            ingress = listOf(LoadBalancerIngress(
                hostname = PLACEHOLDER,
                ip = PLACEHOLDER,
                ipMode = PLACEHOLDER,
                ports = listOf(
                    ServicePortStatus(
                        port = PORT_NUMBER,
                        protocol = Protocol.TCP,
                        error = PLACEHOLDER
                    )
                )
            ))
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Service, Service.Builder> {
            scenario {
                id = "minimum"
                given(Service.Builder())
                expected = Service()
            }

            scenario {
                id = "minimum"
                given(Service.Builder()) {
                    sharedMetadata()
                    spec {
                        selector(PLACEHOLDER to PLACEHOLDER)
                        ports {
                            port {
                                ports = PORT_NUMBER
                                targetPort(PORT_NUMBER)
                                protocol = Protocol.TCP
                                name = PLACEHOLDER
                                nodePort = PORT_NUMBER
                                appProtocol = PLACEHOLDER
                            }
                        }
                        type = PLACEHOLDER
                        ipFamilies(PLACEHOLDER)
                        ipFamilyPolicy = PLACEHOLDER
                        clusterIP = PLACEHOLDER
                        clusterIPs(PLACEHOLDER)
                        externalIPs(PLACEHOLDER)
                        sessionAffinity = PLACEHOLDER
                        loadBalancerIP = PLACEHOLDER
                        loadBalancerSourceRanges(PLACEHOLDER)
                        loadBalancerClass = PLACEHOLDER
                        externalName = PLACEHOLDER
                        externalTrafficPolicy = PLACEHOLDER
                        internalTrafficPolicy = PLACEHOLDER
                        healthCheckNodePort = PORT_NUMBER
                        publishNotReadyAddresses()
                        sessionAffinityConfig {
                            clientIP {
                                timeoutSeconds = 1
                            }
                        }
                        allocateLoadBalancerNodePorts()
                        trafficDistribution = PLACEHOLDER
                    }
                    status {
                        loadBalancer {
                            ingresses {
                                ingress {
                                    hostname = PLACEHOLDER
                                    ip = PLACEHOLDER
                                    ipMode = PLACEHOLDER
                                    ports {
                                        port {
                                            port = PORT_NUMBER
                                            protocol = Protocol.TCP
                                            error = PLACEHOLDER
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                expected = Service(
                    metadata = METADATA,
                    spec = Service.Spec(
                        selector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        ports = listOf(SERVICE_PORT),
                        type = PLACEHOLDER,
                        ipFamilies = listOf(PLACEHOLDER),
                        ipFamilyPolicy = PLACEHOLDER,
                        clusterIP = PLACEHOLDER,
                        clusterIPs = listOf(PLACEHOLDER),
                        externalIPs = listOf(PLACEHOLDER),
                        sessionAffinity = PLACEHOLDER,
                        loadBalancerIP = PLACEHOLDER,
                        loadBalancerSourceRanges = listOf(PLACEHOLDER),
                        loadBalancerClass = PLACEHOLDER,
                        externalName = PLACEHOLDER,
                        externalTrafficPolicy = PLACEHOLDER,
                        internalTrafficPolicy = PLACEHOLDER,
                        healthCheckNodePort = PORT_NUMBER,
                        publishNotReadyAddresses = true,
                        sessionAffinityConfig = SESSION_AFFINITY_CONFIG,
                        allocateLoadBalancerNodePorts = true,
                        trafficDistribution = PLACEHOLDER
                    ),
                    status = Service.Status(
                        loadBalancer = LOAD_BALANCER_STATUS
                    )
                )
            }
        }
    }
}