//package io.violabs.picard.tutorial.pod
//
//import io.violabs.picard.YamlBuilder
//import io.violabs.picard.buildManifest
//
//object SimplePodFactory {
//    fun buildSimplePodDsl(): String {
//        val config = buildManifest {
//            workloadSection {
//                pod {
//                    metadata {
//                        name = "nginx"
//                    }
//
//                    spec {
//                        containers {
//                            container {
//                                name = "nginx"
//                                image = "nginx:1.14.2"
//                                ports {
//                                    addContainerPort {
//                                        containerPort = 80
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return YamlBuilder.build(config)
//    }
//}
//
