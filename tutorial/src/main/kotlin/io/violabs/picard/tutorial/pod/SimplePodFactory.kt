package io.violabs.picard.tutorial.pod

import io.violabs.picard.buildManifest
import io.violabs.picard.buildPodYamlContent
import io.violabs.picard.buildManifestContent
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.pod.Container
import java.time.LocalDateTime

object SimplePodFactory {
    fun buildSimplePod(): String {
        val config = PodResource(
            apiVersion = PodResource.Version.V1,
            kind = Kind.POD,
            metadata = ObjectMetadata(
                name = "nginx"
            ),
            spec = Spec(
                containers = listOf(
                    Container(
                        name = "nginx",
                        image = "nginx:1.14.2",
                        ports = listOf(
                            Container.Port(
                                containerPort = 80
                            )
                        )
                    )
                )
            )
        )

        return buildPodYamlContent(config)
    }

    fun buildSimplePodDsl(): String {
        val config = buildManifest {
            resource {
                apiVersion = PodResource.Version.V1
                kind = Kind.POD
                metadata {
                    name = "nginx"
                }
                spec {
                    containers {
                        container {
                            name = "nginx"
                            image = "nginx:1.14.2"
                            ports {
                                port {
                                    containerPort = 80
                                }
                            }
                        }
                    }
                }
            }
        }

        return buildManifestContent(config)
    }

    fun buildSimpleStatus(): String {
        val config = buildManifest {
            resource {
                apiVersion = PodResource.Version.V1
                kind = Kind.POD
                metadata {
                    name = "nginx"
                }
                spec {
                    readinessGates {
                        readinessGate { conditionType = "www.example.com/feature-1" }
                    }
                }
                status {
                    conditions {
                        condition {
                            type = "Ready"
                            status = "False"
                            lastProbeTime = null
                            lastTransitionTime = LocalDateTime.now()
                        }
                        condition {
                            type = "www.example.com/feature-1"
                            status = "False"
                            lastProbeTime = null
                            lastTransitionTime = LocalDateTime.now()
                        }
                    }
                    containerStatus {
                        containerId = "docker://abcd..."
                        isReady()
                    }
                }
            }
        }

        return buildManifestContent(config)
    }
}

