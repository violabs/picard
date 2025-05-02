package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressTest : SuccessBuildSim<Ingress, Ingress.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val TYPED_OBJECT_REF = TypedLocalObjectReference(
            apiGroup = PLACEHOLDER,
            kind = PLACEHOLDER,
            name = PLACEHOLDER
        )

        private val SERVICE_BACKEND = IngressServiceBackend(
            name = PLACEHOLDER,
            port = IngressServiceBackendPort(
                name = PLACEHOLDER,
                number = PORT_NUMBER
            )
        )

        private val INGRESS_BACKEND = IngressBackend(
            resource = TYPED_OBJECT_REF,
            service = SERVICE_BACKEND
        )

        private fun IngressBackend.Builder.sharedBackend() {
            resource {
                apiGroup = PLACEHOLDER
                kind = PLACEHOLDER
                name = PLACEHOLDER
            }
            service {
                name = PLACEHOLDER
                port {
                    name = PLACEHOLDER
                    number = PORT_NUMBER
                }
            }
        }

        private val INGRESS_RULE = IngressRule(
            host = PLACEHOLDER,
            http = IngressHTTPIngressRuleValue(
                paths = listOf(
                    IngressHTTPIngressPath(
                        path = PLACEHOLDER,
                        pathType = PLACEHOLDER,
                        backend = INGRESS_BACKEND
                    )
                )
            )
        )

        private val TLS = IngressTLS(
            hosts = listOf(PLACEHOLDER),
            secretName = PLACEHOLDER
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Ingress, Ingress.Builder> {
            scenario {
                id = "minimum"
                given(Ingress.Builder())
                expected = Ingress()
            }

            scenario {
                id = "full"
                given(Ingress.Builder()) {
                    sharedMetadata()
                    spec {
                        defaultBackend {
                            sharedBackend()
                        }
                        ingressClassName = PLACEHOLDER
                        rules {
                            rule {
                                host = PLACEHOLDER
                                http {
                                    paths {
                                        path {
                                            path = PLACEHOLDER
                                            pathType = PLACEHOLDER
                                            backend {
                                                sharedBackend()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        tlsList {
                            tlsItem {
                                hosts(PLACEHOLDER)
                                secretName = PLACEHOLDER
                            }
                        }
                    }
                    this.status {
                        loadBalancer {
                            hostname = PLACEHOLDER
                            ip = PLACEHOLDER
                            ports {
                                status {
                                    port = PORT_NUMBER
                                    protocol = Protocol.TCP
                                    error = PLACEHOLDER
                                }
                            }
                        }
                    }
                }
                expected = Ingress(
                    metadata = METADATA,
                    spec = Ingress.Spec(
                        defaultBackend = INGRESS_BACKEND,
                        ingressClassName = PLACEHOLDER,
                        rules = listOf(INGRESS_RULE),
                        tls = listOf(TLS)
                    ),
                    status = Ingress.Status(
                        loadBalancer = IngressLoadBalancerIngress(
                            hostname = PLACEHOLDER,
                            ip = PLACEHOLDER,
                            ports = listOf(
                                IngressPortStatus(
                                    port = PORT_NUMBER,
                                    protocol = Protocol.TCP,
                                    error = PLACEHOLDER
                                )
                            )
                        )
                    )
                )
            }
        }
    }
}