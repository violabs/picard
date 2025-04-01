package io.violabs.picard.tutorial.workload

import io.violabs.picard.buildManifestContent
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.Label
import io.violabs.picard.domain.PodResource

object DeploymentFactory {

    fun buildSimpleDeployment(): String = buildManifestContent {
        resource {
            val label = Label("app", "nginx")

            apiVersion = PodResource.Version.APPS_V1
            kind = Kind.DEPLOYMENT
            metadata {
                name = "nginx-deployment"
                labels {
                    label(label)
                }
            }

            spec {
                replicas = 3
                selector {
                    matchLabels {
                        label(label)
                    }
                }

                template {
                    templateMetadata {
                        labels {
                            label(label)
                        }
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
        }
    }
}