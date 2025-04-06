//package io.violabs.picard.tutorial.podTemplate
//
//import io.violabs.picard.*
//import io.violabs.picard.domain.Kind
//import io.violabs.picard.domain.PodResource
//import io.violabs.picard.domain.RestartPolicy
//
//object PodTemplateFactory {
//    fun buildPodTemplateDsl(): String {
//        val config = buildManifest {
//            resource {
//                apiVersion = PodResource.Version.BATCH_V1
//                kind = Kind.JOB
//                metadata {
//                    name = "hello"
//                }
//                spec {
//                    template {
//                        spec {
//                            containers {
//                                container {
//                                    name = "hello"
//                                    image = "busybox:1.28"
//                                    command = listOf("sh", "-c", "echo 'Hello, Kubernetes!' && sleep 3600")
//                                }
//                            }
//                            restartPolicy = RestartPolicy.ON_FAILURE
//                        }
//                    }
//                }
//            }
//        }
//
//        return buildManifestContent(config)
//    }
//}