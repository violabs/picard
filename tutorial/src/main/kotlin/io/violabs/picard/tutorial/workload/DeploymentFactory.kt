//package io.violabs.picard.tutorial.workload
//
//import io.violabs.picard.buildManifestContent
//import io.violabs.picard.domain.Kind
//import io.violabs.picard.domain.Label
//import io.violabs.picard.domain.PodResource
//import io.violabs.picard.domain.builder.ManifestBuilder
//import io.violabs.picard.domain.builder.SpecBuilder
//
//object DeploymentFactory {
//
//    fun buildSimpleDeployment(): String = buildManifestContent {
//        buildSharedResource()
//    }
//
//    fun buildMaxUnavailableStrategy(): String = buildManifestContent {
//        buildSharedResource {
//            strategy {
//                type = Strategy.Type.ROLLING_UPDATE
//                rollingUpdate {
//                    maxUnavailable = 1
//                }
//            }
//        }
//    }
//
//    fun buildMaxSurgeStrategy(): String = buildManifestContent {
//        buildSharedResource {
//            strategy {
//                type = Strategy.Type.ROLLING_UPDATE
//                rollingUpdate {
//                    maxSurge = 1
//                }
//            }
//        }
//    }
//
//    fun buildHybridStrategy(): String = buildManifestContent {
//        buildSharedResource {
//            strategy {
//                type = Strategy.Type.ROLLING_UPDATE
//                rollingUpdate {
//                    maxUnavailable = 1
//                    maxSurge = 1
//                }
//            }
//        }
//    }
//
//    private fun ManifestBuilder.buildSharedResource(specScope: SpecBuilder.() -> Unit = {}) = resource {
//        val label = Label("app", "nginx")
//
//        apiVersion = PodResource.Version.APPS_V1
//        kind = Kind.DEPLOYMENT
//        metadata {
//            name = "nginx-deployment"
//            labels {
//                label(label)
//            }
//        }
//
//        spec {
//            replicas = 3
//            selector {
//                matchLabels {
//                    label(label)
//                }
//            }
//
//            template {
//                metadata {
//                    labels {
//                        label(label)
//                    }
//                }
//
//                spec {
//                    containers {
//                        container {
//                            name = "nginx"
//                            image = "nginx:1.14.2"
//                            ports {
//                                port {
//                                    containerPort = 80
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            specScope()
//        }
//    }
//}