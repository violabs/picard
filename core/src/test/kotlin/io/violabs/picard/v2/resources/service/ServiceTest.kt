package io.violabs.picard.v2.resources.service

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.service.config.ClientIPConfig
import io.violabs.picard.v2.resources.service.config.SessionAffinityConfig
import io.violabs.picard.v2.resources.service.status.Condition
import io.violabs.picard.v2.resources.service.status.LoadBalancerIngress
import io.violabs.picard.v2.resources.service.status.LoadBalancerStatus
import io.violabs.picard.v2.resources.service.status.PortStatus
import io.violabs.picard.v2.resources.service.status.ServiceStatus
import org.junit.jupiter.api.BeforeAll

class ServiceTest : SuccessBuildSim<Service, ServiceV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Service, ServiceV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceV2DslBuilder())
                expected = Service()
            }

            scenario {
                id = "full"
                given(ServiceV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        selector(PLACEHOLDER to PLACEHOLDER)
                        ports {
                            servicePort {
                                port = 80
                                targetPort = PLACEHOLDER
                                protocol = "TCP"
                                name = PLACEHOLDER
                                nodePort = 30080
                                appProtocol = PLACEHOLDER
                            }
                        }
                        type = "ClusterIP"
                        ipFamilies(PLACEHOLDER)
                        ipFamilyPolicy = PLACEHOLDER
                        clusterIp = PLACEHOLDER
                        clusterIps(PLACEHOLDER)
                        externalIps(PLACEHOLDER)
                        sessionAffinity = PLACEHOLDER
                        loadBalancerIp = PLACEHOLDER
                        loadBalancerSourceRanges(PLACEHOLDER)
                        loadBalancerClass = PLACEHOLDER
                        externalName = PLACEHOLDER
                        externalTrafficPolicy = PLACEHOLDER
                        internalTrafficPolicy = PLACEHOLDER
                        healthCheckNodePort = 30080
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
                        conditions {
                            condition {
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                                status = "True"
                                type = PLACEHOLDER
                                observedGeneration = 1
                            }
                        }
                        loadBalancer {
                            ingress {
                                loadBalancerIngress {
                                    hostname = PLACEHOLDER
                                    ip = PLACEHOLDER
                                    ipMode = PLACEHOLDER
                                    ports {
                                        portStatus {
                                            port = 80
                                            protocol = "TCP"
                                            error = PLACEHOLDER
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                expected = Service(
                    metadata = OBJECT_META,
                    spec = ServiceSpec(
                        selector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        ports = listOf(
                            ServicePort(
                                port = 80,
                                targetPort = PLACEHOLDER,
                                protocol = "TCP",
                                name = PLACEHOLDER,
                                nodePort = 30080,
                                appProtocol = PLACEHOLDER
                            )
                        ),
                        type = "ClusterIP",
                        ipFamilies = listOf(PLACEHOLDER),
                        ipFamilyPolicy = PLACEHOLDER,
                        clusterIp = PLACEHOLDER,
                        clusterIps = listOf(PLACEHOLDER),
                        externalIps = listOf(PLACEHOLDER),
                        sessionAffinity = PLACEHOLDER,
                        loadBalancerIp = PLACEHOLDER,
                        loadBalancerSourceRanges = listOf(PLACEHOLDER),
                        loadBalancerClass = PLACEHOLDER,
                        externalName = PLACEHOLDER,
                        externalTrafficPolicy = PLACEHOLDER,
                        internalTrafficPolicy = PLACEHOLDER,
                        healthCheckNodePort = 30080,
                        publishNotReadyAddresses = true,
                        sessionAffinityConfig = SessionAffinityConfig(
                            clientIP = ClientIPConfig(
                                timeoutSeconds = 1
                            )
                        ),
                        allocateLoadBalancerNodePorts = true,
                        trafficDistribution = PLACEHOLDER
                    ),
                    status = ServiceStatus(
                        conditions = listOf(
                            Condition(
                                lastTransitionTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER,
                                status = "True",
                                type = PLACEHOLDER,
                                observedGeneration = 1
                            )
                        ),
                        loadBalancer = LoadBalancerStatus(
                            ingress = listOf(
                                LoadBalancerIngress(
                                    hostname = PLACEHOLDER,
                                    ip = PLACEHOLDER,
                                    ipMode = PLACEHOLDER,
                                    ports = listOf(
                                        PortStatus(
                                            port = 80,
                                            protocol = "TCP",
                                            error = PLACEHOLDER
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            }
        }
    }
}